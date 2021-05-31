package com.repuestos.dao;

import com.repuestos.entidades.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DaoRepuesto extends JpaRepository<Repuesto, Long> {
    
}
