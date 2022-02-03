package com.repuestos.finnegans.entity;

import com.repuestos.entidades.Maquina;

import java.util.*;

public class WrapperMaquina {
    public static Map<String, Object> toMinimalMap(Maquina maquina) {

        Map<String, Object> map = new HashMap<>();
        map.put("patente", maquina.getPatente());
        map.put("descripcion", maquina.getDescripcion());

        Set<Orden> ordenes = maquina.getOrden();
        ordenes.forEach(orden -> {
            map.put("nro_orden", orden.getNumeroOrden());
            map.put("desc_orden", orden.getDescripcion());
            map.put("proveedor",orden.getProveedor());
            ArrayList<Map<String, Object>> ordenList = new ArrayList<>();
            ordenList.addAll(toOrderMap(orden));
            map.put("orden_detail", ordenList);
        });
        return map;
    }

    public static List<Map<String, Object>> toOrderMap(Orden orden) {
        List<Map<String, Object>> ordenMapList = new ArrayList<>();
        orden.getOrdenDetail().forEach(ordenDetail -> {
            if (ordenDetail.getCantidad() != null) {
                Map<String, Object> orderMap = new HashMap<>();
                orderMap.put("producto", ordenDetail.getProducto());
                orderMap.put("descripcion", ordenDetail.getDescripcion());
                orderMap.put("cantidad", ordenDetail.getCantidad());
                ordenMapList.add(orderMap);
            }
        });
        return ordenMapList;
    }
}
