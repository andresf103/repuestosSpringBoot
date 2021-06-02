package com.repuestos.servicio;

import com.repuestos.entidades.MaquinaRepuesto;
import com.repuestos.dao.DaoMaquinaRepuesto;
import com.repuestos.entidades.Maquina;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaquinaRepuestoServiceImpl implements MaquinaRepuestoService {

    @Autowired
    private DaoMaquinaRepuesto daoMaquinaRepuesto;

    @Transactional(readOnly = true)
    @Override
    public List<MaquinaRepuesto> listarMaquinaRepuestos() {
        return (List<MaquinaRepuesto>) daoMaquinaRepuesto.findAll();
    }

    @Override
    public void guardar(MaquinaRepuesto maquinaRepuesto) {
        daoMaquinaRepuesto.save(maquinaRepuesto);
    }

    @Override
    public void eliminar(MaquinaRepuesto maquinaRepuesto) {
        daoMaquinaRepuesto.delete(maquinaRepuesto);
    }

    @Override
    public MaquinaRepuesto encontrarMaquinaRepuesto(MaquinaRepuesto maquinaRepuesto) {
        maquinaRepuesto = daoMaquinaRepuesto.findById(maquinaRepuesto.getIdMaquinaRepuesto().longValue()).orElse(null);
        return maquinaRepuesto;
    }

    @Override
    public List<MaquinaRepuesto> listarPorMaquina(Maquina maquina) {
       return daoMaquinaRepuesto.findByMaquina(maquina);
    }

}
