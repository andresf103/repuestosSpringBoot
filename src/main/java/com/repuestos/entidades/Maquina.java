
package com.repuestos.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

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

    }
