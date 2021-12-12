package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import com.repuestos.finnegans.dto.OrdenDTO;
import com.repuestos.finnegans.dto.TipoDocumento;
import com.repuestos.finnegans.entity.Orden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrdenRestService extends AbstractRestService {
    private final OrdenEntityService ordenEntityService;

    @Autowired
    public OrdenRestService(OrdenEntityService ordenEntityService) {
        super();
        this.ordenEntityService = ordenEntityService;
    }

    public List<OrdenDTO> findAllFromToday() throws URISyntaxException {
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date today30 = cal.getTime();
        String hoy = new SimpleDateFormat("yyyyMMdd").format(today);
        String antes = new SimpleDateFormat("yyyyMMdd").format(today30);
        String url = String.format(EndPoints.TRACKING.getUrl(), antes, hoy);
        log.info(url);
        ParameterizedTypeReference<List<OrdenDTO>> ordendList;
        ordendList = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<OrdenDTO>> response = executeRequest(url, HttpMethod.GET, ordendList);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            log.info(response.getBody().toString());
            List<OrdenDTO> list = response.getBody();
            List<OrdenDTO> newList=new ArrayList<>();
            list.forEach(ordenDTO -> {
                OrdenDTO ord;
                ord=list.stream().filter(ordenDTO1 -> ordenDTO1.getTransaccionIdInicial().equals(ordenDTO.getTransaccionId())).findFirst().orElse(ordenDTO);
                ordenDTO.setTransaccionIdInicial(ord.getTransaccionId());
                newList.add(ordenDTO);
            });
            return newList.stream()
                    .filter(
                            ordenDTO -> (
                                    ordenDTO.getTipoDocumento().equals(TipoDocumento.ORDEN_DE_COMPRA_REPUESTOS.getDocumento()) || ordenDTO.getTipoDocumento().equals(TipoDocumento.ORDEN_DE_COMPRA.getDocumento())
                            ))
                    .collect(Collectors.toList());
        } else {
            log.error("There was an error\n" + response.getBody());
            throw new RuntimeException("Hubo un error");
        }
    }

    public List<String> idsOrders() throws URISyntaxException {
        List<OrdenDTO> ordenes = findAllFromToday();
        List<OrdenDTO> ordenesADevolver = new ArrayList<>();
        ordenes.forEach(ordenDTO -> {
            if (ordenEntityService.findByTransaccionId(ordenDTO.getTransaccionId()) == null) {
                ordenEntityService.save(new Orden(ordenDTO));

                ordenesADevolver.add(ordenDTO);
            }
        });
        return ordenesADevolver.stream()
                .map(ordenDTO -> ordenDTO.getTransaccionId().toString())
                .collect(Collectors.toList());
    }
}
