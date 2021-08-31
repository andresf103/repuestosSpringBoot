package com.repuestos.dao;

import com.repuestos.entidades.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/** Clase encargada de manejar los datos de las maquinas*/

public interface DaoMaquina extends JpaRepository<Maquina, Long>{
    public List<Maquina> findAllByPatenteContains(String search);
    public List<Maquina> findAllByDescripcionContains(String search);
}


