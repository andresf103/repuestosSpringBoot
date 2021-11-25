package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import com.repuestos.finnegans.dto.OrdenDTO;
import com.repuestos.finnegans.dto.SolicitudDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public OrdenRestService() {
        super();
    }

    public List<OrdenDTO> findAllFromToday() throws URISyntaxException {
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date today30 = cal.getTime();
        String hoy = new SimpleDateFormat("yyyyMMdd").format(today);
        String antes=new SimpleDateFormat("yyyyMMdd").format(today30);
        String url = String.format(EndPoints.TRACKING.getUrl(), antes, hoy);
        log.info(url);
        ParameterizedTypeReference<List<OrdenDTO>> ordendList;
        ordendList = new ParameterizedTypeReference<List<OrdenDTO>>() {
        };
        ResponseEntity<List<OrdenDTO>> response = executeRequest(url, HttpMethod.GET, ordendList);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            log.info(response.getBody().toString());
            List<OrdenDTO> list = response.getBody();
            return list;
        } else {
            log.error("There was an error\n" + response.getBody());
            throw new RuntimeException("Hubo un error");
        }
    }


//Servicio encargado de traer la informacion de las solicitudes en una fecha
//formato de fecha 2021 11 17 SimpleDateFormat("yyyyMMdd").format(new Date())

    //deberiamos ser capaces de crear un metodo de busqueda por transaccion
    //busqueda por numero de solicitud
    // busqueda por usuario

}
