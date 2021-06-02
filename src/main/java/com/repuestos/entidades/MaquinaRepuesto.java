package com.repuestos.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "maquina_repuesto")
public class MaquinaRepuesto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_maquina_repuesto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaquinaRepuesto;

    @JoinColumn(name = "id_repuesto", referencedColumnName = "id_repuesto")
    @ManyToOne
    private Repuesto repuesto;

    @JoinColumn(name = "id_maquina", referencedColumnName = "id_maquina")
    @ManyToOne
    private Maquina maquina;

    @Column(nullable = false, length = 70)
    private String descripcion;

    private String marca;

    public Long getIdMaquinaRepuesto() {
        return idMaquinaRepuesto;
    }

    public void setIdMaquinaRepuesto(Long idMaquinaRepuesto) {
        this.idMaquinaRepuesto = idMaquinaRepuesto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "MaquinaRepuesto{" + "repuesto=" + repuesto + ", maquina=" + maquina + ", descripcion="
                + descripcion + ", marca=" + marca + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.repuesto);
        hash = 67 * hash + Objects.hashCode(this.maquina);
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
        final MaquinaRepuesto other = (MaquinaRepuesto) obj;
        if (!Objects.equals(this.repuesto, other.repuesto)) {
            return false;
        }
        if (!Objects.equals(this.maquina, other.maquina)) {
            return false;
        }
        return true;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

}
