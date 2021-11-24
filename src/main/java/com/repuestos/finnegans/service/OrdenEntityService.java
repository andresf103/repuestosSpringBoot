package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoOrden;
import com.repuestos.finnegans.entity.Orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenEntityService {

    private DaoOrden daoOrden;

    @Autowired
    OrdenEntityService(DaoOrden daoOrden){
        this.daoOrden=daoOrden;
    }

    public Orden save(Orden orden){
        return daoOrden.save(orden);
    }

    public Orden update(Orden orden){
        return daoOrden.save(orden);
    }

    public Orden findByTransaccionId(Long transaccionId ){
        return daoOrden.findByTransaccionId(transaccionId);
    }

}
