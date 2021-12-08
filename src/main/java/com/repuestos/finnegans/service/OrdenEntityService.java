package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoOrden;
import com.repuestos.finnegans.entity.Orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenEntityService {

    private DaoOrden daoOrden;

    @Autowired
    OrdenEntityService(DaoOrden daoOrden) {
        this.daoOrden = daoOrden;
    }

    public Orden save(Orden orden) {
        return daoOrden.save(orden);
    }

    public Orden update(Orden orden) {
        return daoOrden.save(orden);
    }

    public Orden findByTransaccionId(Long transaccionId) {
        Orden orden = daoOrden.findByTransaccionId(transaccionId).orElse(null);
        return orden;
    }

    public List<Orden> findAll() {
        return daoOrden.findAll();
    }

}
