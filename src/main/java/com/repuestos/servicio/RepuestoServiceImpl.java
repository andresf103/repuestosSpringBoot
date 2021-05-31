package com.repuestos.servicio;

import com.repuestos.dao.DaoRepuesto;
import com.repuestos.entidades.Repuesto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepuestoServiceImpl implements RepuestoService {
    
    @Autowired
    private DaoRepuesto daoRepuesto;

    @Override
    @Transactional(readOnly = true)
    public List<Repuesto> listarRepuestos() {
        return (List<Repuesto>) daoRepuesto.findAll();
    }

    @Override
    public void guardar(Repuesto repuesto) {
        daoRepuesto.save(repuesto);
    }

    @Override
    public void eliminar(Repuesto repuesto) {
    daoRepuesto.delete(repuesto);
    }

    @Override
    public Repuesto encontrarRepuesto(Repuesto repuesto) {
    return daoRepuesto.findById(repuesto.getIdRepuesto().longValue()).orElse(null);
    }
    
}
