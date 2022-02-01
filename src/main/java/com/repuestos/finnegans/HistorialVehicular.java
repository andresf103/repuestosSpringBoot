package com.repuestos.finnegans;

import com.repuestos.entidades.Maquina;
import com.repuestos.finnegans.dto.OrdenDetailDTO;
import com.repuestos.finnegans.entity.Orden;
import com.repuestos.finnegans.entity.OrdenDetail;
import com.repuestos.finnegans.service.OrdenDetailEntityService;
import com.repuestos.finnegans.service.OrdenDetailRestService;
import com.repuestos.finnegans.service.OrdenEntityService;
import com.repuestos.servicio.MaquinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("SpellCheckingInspection")
@Component
@Slf4j
public class HistorialVehicular {

    private final OrdenDetailEntityService entityService;
    private final OrdenDetailRestService ordenDetailRestService;
    private final OrdenEntityService ordenEntityService;
    private final MaquinaService maquinaService;

    @Autowired
    public HistorialVehicular(OrdenDetailEntityService entityService, OrdenDetailRestService ordenDetailRestService, OrdenEntityService ordenEntityService, MaquinaService maquinaService) {
        this.entityService = entityService;
        this.ordenDetailRestService = ordenDetailRestService;
        this.ordenEntityService = ordenEntityService;
        this.maquinaService = maquinaService;
    }

    public void guardarDetalleOrden(OrdenDetailDTO ordenDetailDTO) {
        Optional<Orden> orden = ordenEntityService.findByTransactionId(ordenDetailDTO.getTransactionId());
        orden.ifPresent(value -> {
            if(value.getOrdenDetail().isEmpty()){
                entityService.save(new OrdenDetail(ordenDetailDTO, value));
            }
        });
    }

    public List<OrdenDetail> obtenerDetalleOrden(Orden orden) throws URISyntaxException {
        if (orden.getOrdenDetail().isEmpty()) {
            List<OrdenDetail> listOrdenDetail = ordenDetailRestService.findByOrden(orden);
            //log.info(listOrdenDetail.toString());
            if (!listOrdenDetail.isEmpty()) {
                for (OrdenDetail detail : listOrdenDetail) {
                    guardarDetalleOrden(detail.toDTO());
                }
                orden.setOrdenDetail(listOrdenDetail);
            }
        }
        return orden.getOrdenDetail();
    }

    //dada una lista de patentes vehiculares comparar con una lista de ordenes de compra y clasificar el historial
    public void clasificarOrdenes(List<Maquina> maquinas, List<Orden> ordenes) {
        Map<Maquina, String> patentes = maquinas.stream().collect(Collectors.toMap(maquina -> maquina, Maquina::getPatente));
        Map<Orden, String> ordenesMap = ordenes.stream().collect(Collectors.toMap(orden -> orden, Orden::getDescripcion));
        patentes.forEach((maquina, patente) -> {
                    ordenesMap.forEach((orden, descripcion) -> {
                        String descripcionSinEspacios = descripcion.replaceAll(" ", "");
                        if (descripcionSinEspacios.contains(patente)) {
                            maquina.getOrden().add(orden);
                            maquinaService.guardar(maquina);
                            try {
                                orden.getOrdenDetail().addAll(obtenerDetalleOrden(orden));
                                log.info(maquina.getPatente()+ " encontrado en la orden "+ orden.getNumeroOrden() +" "+ orden.getDescripcion());
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                            ordenEntityService.save(orden);
                        }
                    });
                }
        );
    }

    public void rutina(){
        List<Maquina> maquinas=maquinaService.listarMaquinas();
        List<Orden> ordenes= ordenEntityService.findAll();
        clasificarOrdenes(maquinas,ordenes);
    }

    /*Tenemos que crear la funcion que busque de la lista de vehiculos alguna coincidencia
     dentro de la orden de compra
    */

//tenemos que crear una tabla que guarde la relacion entre la patente vehicular y la orden de compra

//debemos chequear antes de guardar la relacion que la orden de compra ya éste asociada a una factura.

//el chequeo deberia correr diariamente.

//tenemos que crear la vista que muestre el historial de cada vehiculo
}
