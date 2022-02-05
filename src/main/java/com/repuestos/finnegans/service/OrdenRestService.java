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
    ordenesPorMes(1);
    }

    public void findAllFromYear(int year) throws URISyntaxException {
        for (int i = 0; i < 12; i++) {
            ordenesPorMes((year*12)+i);
        }
    }

    public void ordenesPorMes(int mesAtrazado) throws URISyntaxException {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        int mesi=mesAtrazado*-30;
        cal.add(Calendar.DAY_OF_MONTH, mesi);
        Date antes30 = cal.getTime();
        cal.setTime(antes30);
        cal.add(Calendar.DAY_OF_MONTH,30);
        Date despues30=cal.getTime();
        String despues = new SimpleDateFormat("yyyyMMdd").format(despues30);
        String antes = new SimpleDateFormat("yyyyMMdd").format(antes30);
        String url = String.format(EndPoints.ORDEN.getUrl(),antes,despues);
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