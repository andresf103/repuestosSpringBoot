package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DaoSolicitud extends JpaRepository<Solicitud, Long> {

    Optional<Solicitud> findByTransactionId(Long transactionId);

}
