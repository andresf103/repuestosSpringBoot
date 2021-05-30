package com.repuestos.dao;

import com.repuestos.entidades.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;


/** Clase encargada de manejar los datos de las maquinas*/

public interface DaoMaquina extends JpaRepository<Maquina, Long>{
    
}
