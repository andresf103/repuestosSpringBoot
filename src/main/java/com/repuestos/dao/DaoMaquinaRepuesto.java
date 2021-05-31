
package com.repuestos.dao;

import com.repuestos.entidades.MaquinaRepuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoMaquinaRepuesto extends JpaRepository<MaquinaRepuesto, Long>{
    
}
