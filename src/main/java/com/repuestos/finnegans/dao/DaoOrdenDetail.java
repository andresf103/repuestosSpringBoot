package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Orden;
import com.repuestos.finnegans.entity.OrdenDetail;
import org.springframework.data.jpa.repository.JpaRepository;;

import java.util.List;
import java.util.Optional;

public interface DaoOrdenDetail extends JpaRepository<OrdenDetail, Long> {
    Optional<List<OrdenDetail>> findAllByOrden(Orden orden);
}
