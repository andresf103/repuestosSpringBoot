package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoSolicitud;
import com.repuestos.finnegans.entity.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudEntityService {

    private DaoSolicitud daoSolicitud;

    @Autowired
    SolicitudEntityService(DaoSolicitud daoSolicitud) {
        this.daoSolicitud = daoSolicitud;
    }

    public Solicitud save(Solicitud solicitud) {
        return daoSolicitud.save(solicitud);
    }

    public Solicitud update(Solicitud solicitud) {
        return daoSolicitud.save(solicitud);
    }

    public Optional<Solicitud> findByTransaccionId(Long transaccionId) {
        return daoSolicitud.findByTransactionId(transaccionId);
    }

    public List<Solicitud> findAll() {
        return daoSolicitud.findAll();
    }

}
