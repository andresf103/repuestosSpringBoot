package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
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
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SolicitudRestService extends AbstractRestService {


    @Autowired
    public SolicitudRestService(){
        super();
    }

    public List<SolicitudDTO> findAllFromToday() throws URISyntaxException {
        String hoy=new SimpleDateFormat("yyyyMMdd").format(new Date());
        String url = String.format(EndPoints.SOLICITUD.getUrl(),hoy,hoy);
        log.info(url);
        ParameterizedTypeReference<List<SolicitudDTO>> solicitudList;
        solicitudList = new ParameterizedTypeReference<List<SolicitudDTO>>() {
        };
        ResponseEntity<List<SolicitudDTO>> response = executeRequest(url, HttpMethod.GET, solicitudList);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            log.info(response.getBody().toString());
            List<SolicitudDTO> list = response.getBody();
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
