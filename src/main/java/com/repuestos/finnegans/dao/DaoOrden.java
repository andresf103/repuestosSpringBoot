package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Orden;
import com.repuestos.finnegans.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DaoOrden extends JpaRepository<Orden,Long> {

    Optional<Orden> findByTransactionId(Long transaccionId);
}
