package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoOrden;
import com.repuestos.finnegans.entity.Orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Orden> findByTransactionId(Long transactionId) {
        return daoOrden.findByTransactionId(transactionId);
    }

    public List<Orden> findAll() {
        return daoOrden.findAll();
    }

    public List<Orden> findLastOnes(){
        Pageable page= PageRequest.of(0, 500);
        return daoOrden.findTopOrderDesc(page).getContent();
    }

}