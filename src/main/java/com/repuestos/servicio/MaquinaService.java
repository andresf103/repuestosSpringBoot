
package com.repuestos.servicio;

import com.repuestos.entidades.Maquina;
import java.util.List;

public interface MaquinaService {
    public List<Maquina> listarMaquinas();
    
    public void guardar(Maquina maquina);
    
    public void eliminar(Maquina maquina);
    
    public Maquina encontrarMaquina(Maquina maquina);
}
