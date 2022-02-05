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
            Map <String,Object> ordermap=new HashMap<>();
            ordermap.put("nro_orden", orden.getNumeroOrden());
            ordermap.put("desc_orden", orden.getDescripcion());
            ordermap.put("proveedor",orden.getProveedor());
            ArrayList<Map<String, Object>> ordenList = new ArrayList<>();
            ordenList.addAll(toOrderMap(orden));
            ordermap.put("orden_detail", ordenList);
            map.put(orden.getNumeroOrden(),ordermap);
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
