package com.repuestos.servicio;

import com.repuestos.entidades.MaquinaRepuesto;
import java.util.List;

public interface MaquinaRepuestoService {

    public List<MaquinaRepuesto> listarMaquinaRepuestos();

    public void guardar(MaquinaRepuesto maquinaRepuesto);

    public void eliminar(MaquinaRepuesto maquinaRepuesto);

    public MaquinaRepuesto encontrarMaquinaRepuesto(MaquinaRepuesto maquinaRepuesto);
}
