package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import com.repuestos.finnegans.dto.OrdenDetailDTO;
import com.repuestos.finnegans.entity.Orden;
import com.repuestos.finnegans.entity.OrdenDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrdenDetailRestService extends AbstractRestService {
    private final OrdenEntityService ordenEntityService;
    private final OrdenDetailEntityService ordenDetailEntityService;

    @Autowired
    public OrdenDetailRestService(OrdenEntityService ordenEntityService, OrdenDetailEntityService ordenDetailEntityService) {
        super();
        this.ordenEntityService = ordenEntityService;
        this.ordenDetailEntityService = ordenDetailEntityService;
    }

    public List<OrdenDetail> findByOrden(Orden orden) throws URISyntaxException {
        if (orden.getOrdenDetail().isEmpty()) {
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
            Date dateOrden = null;
            try {
                dateOrden = formatter1.parse(orden.getFecha());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String fechaOrden = new SimpleDateFormat("yyyyMMdd").format(dateOrden);
            String url = String.format(EndPoints.ORDENDETAIL.getUrl(), fechaOrden, fechaOrden);
            log.info(url);

            ParameterizedTypeReference<List<OrdenDetailDTO>> ordenList;
            ordenList = new ParameterizedTypeReference<>() {
            };
            try {
            ResponseEntity<List<OrdenDetailDTO>> response = executeRequest(url, HttpMethod.GET, ordenList);

            if (HttpStatus.OK.equals(response.getStatusCode())) {
                List<OrdenDetailDTO> list = response.getBody();
                list.forEach(ordenDetailDTO -> {
                    if (orden.getTransactionId().equals(ordenDetailDTO.getTransactionId())) {
                        try {
                            if(ordenDetailDTO.getPrecio()!=null){
                            ordenDetailEntityService.save(new OrdenDetail(ordenDetailDTO, orden));
                            }
                        } catch (Exception e){
                            log.error("ordendetail " + ordenDetailDTO.toString());
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                log.error("There was an error\n" + response.getBody());
                throw new RuntimeException("Hubo un error");
            }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        Optional<Orden> optionalOrden = ordenEntityService.findByTransactionId(orden.getTransactionId());
        if (optionalOrden.isPresent())
            return optionalOrden.get().getOrdenDetail();
        else return new ArrayList<>();
    }

}
