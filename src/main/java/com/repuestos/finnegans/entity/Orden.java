package com.repuestos.finnegans.entity;

import com.repuestos.entidades.Maquina;
import com.repuestos.finnegans.dto.OrdenDTO;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "orden")
public class Orden implements Serializable {

    @Id
    @NotNull
    private Long transactionId;

    @ManyToMany(mappedBy = "orden",fetch = FetchType.EAGER)
    Set<Maquina> maquina;

    @Column(length = 500)
    private String descripcion;
    @Column(length = 150)
    private String proveedor;
    private String numeroOrden;
    private String fecha;

     @OneToMany(mappedBy="orden", fetch = FetchType.EAGER)
    private List<OrdenDetail> ordenDetail = new ArrayList<>();

    public Orden(OrdenDTO ordenDTO) {
        this.transactionId = ordenDTO.getTransactionId();
        this.descripcion = ordenDTO.getDescripcion();
        this.proveedor = ordenDTO.getProveedor();
        this.numeroOrden = ordenDTO.getNumeroOrden();
        this.fecha=ordenDTO.getFecha();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Orden orden = (Orden) o;
        return transactionId != null && Objects.equals(transactionId, orden.transactionId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
