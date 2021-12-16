package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import com.repuestos.finnegans.dto.ProveedorDTO;
import com.repuestos.finnegans.entity.Proveedor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Service
public class ProveedorRestService extends AbstractRestService {

    private ProveedorEntityService proveedorEntityService;

    @Autowired
    public ProveedorRestService(ProveedorEntityService proveedorEntityService)
    {
        super();
        this.proveedorEntityService=proveedorEntityService;
    }

    public void findAll() throws URISyntaxException {

        String url = String.format(EndPoints.PROVEEDORES.getUrl());
        log.info(url);

        ParameterizedTypeReference<List<ProveedorDTO>> proveedorlist;
        proveedorlist = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<ProveedorDTO>> response = executeRequest(url, HttpMethod.GET, proveedorlist);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            log.info(response.getBody().toString());
            List<ProveedorDTO> list = response.getBody();
            list.forEach(proveedorDto -> {
               proveedorEntityService.save(new Proveedor(proveedorDto));
            });
        } else {
            log.error("There was an error\n" + response.getBody());
            throw new RuntimeException("Hubo un error");
        }
    }
}
