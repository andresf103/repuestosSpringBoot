package com.repuestos.finnegans.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Data
@Entity
@Table(name="proveedor")
public class Proveedor implements Serializable {

    @Id
    @Column(name="id_proveedor", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;
    @NotEmpty
    private String cuit;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String email;
    private boolean enviar=false;

}
