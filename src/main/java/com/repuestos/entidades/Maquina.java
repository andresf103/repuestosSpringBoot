
package com.repuestos.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="maquina")
public class Maquina implements Serializable {
    @Id
    @Column(name="id_maquina", nullable=false, length=7)
    private String idMaquina;
    @Column(nullable=false, length=70)
    private String descripcion;
    @Column(length=70)
    private String nSerie;
    @Column(length=70)
    private String nMotor;
    @Column(length=70)
    private String modelo;

    @Override
    public String toString() {
        return "Maquina{" + "idMaquina=" + idMaquina + ", descripcion=" + descripcion + ", nSerie=" + nSerie + ", nMotor=" + nMotor + ", modelo=" + modelo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.idMaquina);
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
        final Maquina other = (Maquina) obj;
        if (!Objects.equals(this.idMaquina, other.idMaquina)) {
            return false;
        }
        return true;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public String getnMotor() {
        return nMotor;
    }

    public void setnMotor(String nMotor) {
        this.nMotor = nMotor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(String idMaquina) {
        this.idMaquina = idMaquina;
    }
    
    }
