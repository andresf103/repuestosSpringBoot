package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import com.repuestos.finnegans.dto.OrdenDTO;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
@Service
public class OrdenRestService extends AbstractRestService {

    private OrdenEntityService ordenEntityService;

    @Autowired
    public OrdenRestService(OrdenEntityService ordenEntityService)
    {
        super();
        this.ordenEntityService=ordenEntityService;
    }

    @Scheduled(fixedDelayString = "${syncSolicitudesInterval}",initialDelay = 80000L)
    public void findAllFromToday() throws URISyntaxException {

        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date today30 = cal.getTime();
        String hoy = new SimpleDateFormat("yyyyMMdd").format(today);
        String antes = new SimpleDateFormat("yyyyMMdd").format(today30);
        String url = String.format(EndPoints.ORDEN.getUrl(),antes,hoy);
        log.info(url);

        ParameterizedTypeReference<List<OrdenDTO>> ordenList;
        ordenList = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<OrdenDTO>> response = executeRequest(url, HttpMethod.GET, ordenList);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            log.info(response.getBody().toString());
            List<OrdenDTO> list = response.getBody();
            list.forEach(ordenDTO -> {
                if(!ordenEntityService.findByTransactionId(ordenDTO.getTransactionId()).isPresent()){
                    ordenEntityService.save(new Orden(ordenDTO));
                }
            });
        } else {
            log.error("There was an error\n" + response.getBody());
            throw new RuntimeException("Hubo un error");
        }
    }

}
/*








}*/