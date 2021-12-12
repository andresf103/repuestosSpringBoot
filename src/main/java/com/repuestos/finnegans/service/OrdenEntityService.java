package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoMail;
import com.repuestos.finnegans.dao.DaoOrden;
import com.repuestos.finnegans.entity.Mail;
import com.repuestos.finnegans.entity.Orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenEntityService {

    private DaoOrden daoOrden;
    private DaoMail daoMail;

    @Autowired
    OrdenEntityService(DaoOrden daoOrden,DaoMail daoMail) {
        this.daoOrden = daoOrden;
        this.daoMail=daoMail;
    }

    public Orden save(Orden orden) {
        Mail mail=new Mail(orden);
        daoMail.save(mail);
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
