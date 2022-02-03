
package com.repuestos.entidades;

import com.repuestos.finnegans.entity.Orden;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "historial_vehicular",
            joinColumns = @JoinColumn(name = "id_maquina"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    Set<Orden> orden;
    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Maquina maquina = (Maquina) o;
        return idMaquina != null && Objects.equals(idMaquina, maquina.idMaquina);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "idMaquina=" + idMaquina +
                ", patente='" + patente + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", nSerie='" + nSerie + '\'' +
                ", nMotor='" + nMotor + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
