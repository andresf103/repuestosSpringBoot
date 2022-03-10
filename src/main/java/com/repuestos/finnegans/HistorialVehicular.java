package com.repuestos.finnegans;

import com.repuestos.entidades.Maquina;
import com.repuestos.finnegans.entity.Orden;
import com.repuestos.finnegans.service.OrdenDetailRestService;
import com.repuestos.finnegans.service.OrdenEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("SpellCheckingInspection")
@Component
@Slf4j
public class HistorialVehicular {


    private final OrdenDetailRestService ordenDetailRestService;
    private final OrdenEntityService ordenEntityService;
    private final com.repuestos.servicio.MaquinaService maquinaService;

    @Autowired
    public HistorialVehicular(OrdenDetailRestService ordenDetailRestService, OrdenEntityService ordenEntityService, com.repuestos.servicio.MaquinaService maquinaService) {
        this.ordenDetailRestService = ordenDetailRestService;
        this.ordenEntityService = ordenEntityService;
        this.maquinaService = maquinaService;
    }

    //dada una lista de patentes vehiculares comparar con una lista de ordenes de compra y clasificar el historial
    public void clasificarOrdenes(List<Maquina> maquinas, List<Orden> ordenes) {
        Map<Maquina, String> patentes = maquinas.stream().collect(Collectors.toMap(maquina -> maquina, Maquina::getPatente));
        Map<Orden, String> ordenesMap = ordenes.stream().collect(Collectors.toMap(orden -> orden, Orden::getDescripcion));
        patentes.forEach((maquina, patente) -> {
            ordenesMap.forEach((orden, descripcion) -> {
                if (!maquina.getOrden().contains(orden)) {
                    String descripcionSinEspacios = descripcion.replaceAll(" ", "").toUpperCase(Locale.ROOT);
                    if (descripcionSinEspacios.contains(patente)) {
                        maquina.getOrden().add(orden);
                        try {
                            if (orden.getOrdenDetail().isEmpty()) {
                                orden.getOrdenDetail().addAll(ordenDetailRestService.findByOrden(orden));
                            }
                            log.info(maquina.getPatente() + " encontrado en la orden " + orden.getNumeroOrden() + " " + orden.getDescripcion());
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        ordenEntityService.save(orden);
                    }
                }
            });
            try{
                    maquinaService.guardar(maquina);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        );
    }

    @Scheduled(fixedDelay = 86400000L, initialDelay = 10000L)
    public void rutina() {
        List<Maquina> maquinas = maquinaService.listarMaquinas();
        List<Orden> ordenes = ordenEntityService.findLastOnes();
        clasificarOrdenes(maquinas, ordenes);
    }

    public void historialVehicularPorPatente(String patente) {
        List<Maquina> maquinas = maquinaService.listarMaquinas();
        List<Maquina> maquina=maquinas.stream().filter(maquinita -> maquinita.getPatente().equals(patente)).collect(Collectors.toList());
        List<Orden> ordenes = ordenEntityService.findAll();
        clasificarOrdenes(maquina, ordenes);
    }

//cuando mostramos las ordenes de una maquina primero las filtramos si la orden esta facturada.

//tenemos que crear la vista que muestre el historial de cada vehiculo
}
