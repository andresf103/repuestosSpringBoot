
package com.repuestos.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name="maquina")
public class Maquina implements Serializable {
    @Id
    @Column(name="id_maquina", nullable=false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idMaquina;
    
    @Column(name="patente", nullable=false, length=7)
    @NotEmpty
    private String patente;
    
    @Column(nullable=false, length=70)
    @NotEmpty
    private String descripcion;
    @Column(length=70)
    @NotEmpty
    private String nSerie;
    @Column(length=70)
    @NotEmpty
    private String nMotor;
    @Column(length=70)
    @NotEmpty
    private String modelo;

    public Long getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Long idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.patente);
        return hash;
    }

    @Override
    public String toString() {
        return "Maquina{" + "idMaquina=" + idMaquina + ", patente=" + patente + ", descripcion=" + descripcion + ", nSerie=" + nSerie + ", nMotor=" + nMotor + ", modelo=" + modelo + '}';
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
   
    }
