package com.repuestos.finnegans.entity;

import com.repuestos.finnegans.dto.OrdenDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="orden")
public class Orden implements Serializable {

    @Id
    @NotNull
    private Long transactionId;

    @Column(length=500)
    private String descripcion;
    @Column(length=150)
    private String proveedor;
    private String numeroOrden;

    public Orden(OrdenDTO ordenDTO){
        this.transactionId=ordenDTO.getTransactionId();
        this.descripcion=ordenDTO.getDescripcion();
        this.proveedor=ordenDTO.getProveedor();
        this.numeroOrden=ordenDTO.getNumeroOrden();
    }
}
