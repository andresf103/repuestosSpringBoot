package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoSolicitud extends JpaRepository<Solicitud, Long> {

    Solicitud findByTransactionId(Long transactionId);

}
