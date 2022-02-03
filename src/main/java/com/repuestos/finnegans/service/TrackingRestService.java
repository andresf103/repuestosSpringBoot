package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import com.repuestos.finnegans.dto.TipoDocumento;
import com.repuestos.finnegans.dto.TrackingDTO;
import com.repuestos.finnegans.entity.Orden;
import com.repuestos.finnegans.entity.Tracking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrackingRestService extends AbstractRestService {
    private final TrackingEntityService trackingEntityService;
    private final OrdenEntityService ordenEntityService;

    @Autowired
    public TrackingRestService(TrackingEntityService trackingEntityService, OrdenEntityService ordenEntityService) {
        super();
        this.trackingEntityService = trackingEntityService;
        this.ordenEntityService = ordenEntityService;
    }

    public void setOrdenFacturada(List<TrackingDTO> lista) {
        lista.forEach(trackingDTO -> {
            Optional<Orden> ordenOptional=ordenEntityService
                    .findByTransactionId(trackingDTO.getTransactionIdInicial());
            if(ordenOptional.isPresent()){
                Orden orden=ordenOptional.get();
                orden.setFacturado(true);
                ordenEntityService.save(orden);
            }
        } );

    }

    public List<TrackingDTO> findAllFromToday() throws URISyntaxException {
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date today30 = cal.getTime();
        String hoy = new SimpleDateFormat("yyyyMMdd").format(today);
        String antes = new SimpleDateFormat("yyyyMMdd").format(today30);
        String url = String.format(EndPoints.TRACKING.getUrl(), antes, hoy);
        log.info(url);
        ParameterizedTypeReference<List<TrackingDTO>> ordendList;
        ordendList = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<TrackingDTO>> response = executeRequest(url, HttpMethod.GET, ordendList);

        if (HttpStatus.OK.equals(response.getStatusCode())) {
            log.info(response.getBody().toString());
            List<TrackingDTO> list = response.getBody();

            List <TrackingDTO> listTrackingFacturas=list.stream().filter(ordenDTO1->
                            ordenDTO1.getTipoDocumento().equals(TipoDocumento.FACTURA_DE_COMPRA.getDocumento())
                                    ||ordenDTO1.getTipoDocumento().equals(TipoDocumento.ORDEN_DE_COMPRA_REPUESTOS.getDocumento()))
                    .collect(Collectors.toList());
            setOrdenFacturada(listTrackingFacturas);

            List<TrackingDTO> listTracking=list.stream().filter(ordenDTO1->
                    ordenDTO1.getTipoDocumento().equals(TipoDocumento.ORDEN_DE_COMPRA_REPUESTOS.getDocumento())
                            ||ordenDTO1.getTipoDocumento().equals(TipoDocumento.ORDEN_DE_COMPRA.getDocumento()))
                    .collect(Collectors.toList());
            return listTracking;
        } else {
            log.error("There was an error\n" + response.getBody());
            throw new RuntimeException("Hubo un error");
        }
    }

    public List<TrackingDTO> listTracking() throws URISyntaxException {
        List<TrackingDTO> trackings = findAllFromToday();
        List<TrackingDTO> ordenesADevolver = new ArrayList<>();
        trackings.forEach(trackingDTO -> {
            if (trackingEntityService.findByTransactionId(trackingDTO.getTransactionId()) == null) {
                trackingEntityService.save(new Tracking(trackingDTO));
                ordenesADevolver.add(trackingDTO);
            }
        });
        return ordenesADevolver;
    }
}
