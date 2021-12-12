package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import com.repuestos.finnegans.dto.SolicitudDTO;
import com.repuestos.finnegans.entity.Solicitud;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
@Service
public class SolicitudRestService extends AbstractRestService {

    private SolicitudEntityService solicitudEntityService;

    @Autowired
    public SolicitudRestService(SolicitudEntityService solicitudEntityService)
    {
        super();
        this.solicitudEntityService=solicitudEntityService;
    }
    @Scheduled(fixedDelayString = "${syncSolicitudesInterval}")
    public void findAllFromToday() throws URISyntaxException {

        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date today30 = cal.getTime();
        String hoy = new SimpleDateFormat("yyyyMMdd").format(today);
        String antes = new SimpleDateFormat("yyyyMMdd").format(today30);
        String url = String.format(EndPoints.SOLICITUD.getUrl(),antes,hoy);
        log.info(url);

        ParameterizedTypeReference<List<SolicitudDTO>> solicitudList;
        solicitudList = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<SolicitudDTO>> response = executeRequest(url, HttpMethod.GET, solicitudList);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            log.info(response.getBody().toString());
            List<SolicitudDTO> list = response.getBody();
            list.forEach(solicitudDTO -> {
            if(!solicitudEntityService.findByTransaccionId(solicitudDTO.getTransactionId()).isPresent()){
                solicitudEntityService.save(new Solicitud(solicitudDTO));
            }
            });
        } else {
            log.error("There was an error\n" + response.getBody());
            throw new RuntimeException("Hubo un error");
        }
    }
}
