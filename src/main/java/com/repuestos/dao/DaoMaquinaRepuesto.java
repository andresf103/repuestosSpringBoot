
package com.repuestos.dao;

import com.repuestos.entidades.Maquina;
import com.repuestos.entidades.MaquinaRepuesto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoMaquinaRepuesto extends JpaRepository<MaquinaRepuesto, Long>{
    
    List<MaquinaRepuesto> findByMaquina(Maquina maquina);
}
