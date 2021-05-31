/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repuestos.servicio;

import com.repuestos.entidades.Repuesto;
import java.util.List;

public interface RepuestoService {

    public List<Repuesto> listarRepuestos();

    public void guardar(Repuesto repuesto);

    public void eliminar(Repuesto repuesto);

    public Repuesto encontrarRepuesto(Repuesto repuesto);
}
