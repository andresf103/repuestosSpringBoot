
package com.repuestos.servicio;

import com.repuestos.dao.DaoMaquina;
import com.repuestos.entidades.Maquina;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaquinaServiceImpl implements MaquinaService {
    @Autowired
    private DaoMaquina daoMaquina;

    @Override
    @Transactional(readOnly = true)
    public List<Maquina> listarMaquinas() {
        return (List<Maquina>) daoMaquina.findAll();
    }

    @Override
    public void guardar(Maquina maquina) {
        daoMaquina.save(maquina);
    }

    @Override
    public void eliminar(Maquina maquina) {
        daoMaquina.delete(maquina);
    }

    @Override
     @Transactional(readOnly = true)
    public Maquina encontrarMaquina(Maquina maquina) {
       return daoMaquina.findById(maquina.getIdMaquina().longValue()).orElse(null);
    }
    
}
