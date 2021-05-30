/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repuestos.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="repuesto")
public class Repuesto implements Serializable {
    @Id
    @Column(name="id_repuesto", nullable=false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idRepuesto;
    @Column(nullable=false, length=90)
    private String descripcion;

    public Long getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(Long idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Repuesto other = (Repuesto) obj;
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }
    
}
