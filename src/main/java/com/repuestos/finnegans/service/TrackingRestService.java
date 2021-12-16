package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import com.repuestos.finnegans.dto.TrackingDTO;
import com.repuestos.finnegans.dto.TipoDocumento;
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

    @Autowired
    public TrackingRestService(TrackingEntityService trackingEntityService) {
        super();
        this.trackingEntityService = trackingEntityService;
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
            List<TrackingDTO> newList=new ArrayList<>();

            List<TrackingDTO> listSolicitud=list.stream().filter(ordenDTO1->
                    ordenDTO1.getTipoDocumento().equals(TipoDocumento.SOLICITUD_REPUESTOS.getDocumento())
                            ||ordenDTO1.getTipoDocumento().equals(TipoDocumento.SOLICITUD.getDocumento()))
                    .collect(Collectors.toList());
            return listSolicitud;
        } else {
            log.error("There was an error\n" + response.getBody());
            throw new RuntimeException("Hubo un error");
        }
    }

    public List<TrackingDTO> idsOrders() throws URISyntaxException {
        List<TrackingDTO> trackings = findAllFromToday();
        List<TrackingDTO> ordenesADevolver = new ArrayList<>();
        trackings.forEach(trackingDTO -> {
            if (trackingEntityService.findByTransactionId(trackingDTO.getTransactionId()) == null) {
                trackingEntityService.save(new Tracking(trackingDTO));

                ordenesADevolver.add(trackingDTO);
            }
        });
        return ordenesADevolver;
        /*return ordenesADevolver.stream()
                .map(ordenDTO -> ordenDTO.getTransactionIdInicial().toString())
                .collect(Collectors.toList());*/
    }
}
