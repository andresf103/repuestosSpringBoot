package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DaoProveedor extends JpaRepository<Proveedor,Long> {

    Optional<Proveedor> findByNombre(String nombre);
}
