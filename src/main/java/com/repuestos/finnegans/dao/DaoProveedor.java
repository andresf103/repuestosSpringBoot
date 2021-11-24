package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoProveedor extends JpaRepository<Proveedor,Long> {
}
