package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoOrden extends JpaRepository<Orden,Long> {

    public Orden findByTransaccionId(Long transaccionId);
}
