package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoOrdenDetail;
import com.repuestos.finnegans.entity.Orden;
import com.repuestos.finnegans.entity.OrdenDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenDetailEntityService {

    private final DaoOrdenDetail daoOrden;

    @Autowired
    OrdenDetailEntityService(DaoOrdenDetail daoOrden) {
        this.daoOrden = daoOrden;
    }

    public OrdenDetail save(OrdenDetail ordenDetail) {
        return daoOrden.save(ordenDetail);
    }


    public Optional<List<OrdenDetail>> findByTransactionId(Orden orden) {
        return daoOrden.findAllByOrden(orden);
    }

    public List<OrdenDetail> findAll() {
        return daoOrden.findAll();
    }

}
