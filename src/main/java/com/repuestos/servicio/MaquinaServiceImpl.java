
package com.repuestos.servicio;

import com.repuestos.dao.DaoMaquina;
import com.repuestos.entidades.Maquina;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Override
    @Transactional(readOnly = true)
    public List<Maquina> listarMaquinas(String search){
        Map<String,Maquina> listaMaquinas = new HashMap<>();
        List<Maquina>maquinas=daoMaquina.findAllByPatenteContains(search);
        maquinas.stream().forEach(maq->{
            listaMaquinas.put(maq.getPatente(),maq);
        });
        maquinas=daoMaquina.findAllByDescripcionContains(search);
        maquinas.stream().forEach(maq->{
            listaMaquinas.put(maq.getPatente(),maq);
        });
        return listaMaquinas.values().stream().collect(Collectors.toList());
    }
}
