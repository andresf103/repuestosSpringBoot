package com.repuestos.finnegans.entity;

import com.repuestos.finnegans.dto.OrdenDetailDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "orden_detail")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrdenDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Orden orden;

    @Column(length = 500)
    private String Descripcion;
    @Column(length = 500)
    private String producto;
    private Long cantidad;
    private BigDecimal precio;

    public OrdenDetail(OrdenDetailDTO dto,Orden orden){
        this.orden=orden;
        this.Descripcion= dto.getDescItem();
        this.producto=dto.getProducto();
        this.cantidad=dto.getCantidad();
        this.precio=dto.getPrecio();
    }

    public OrdenDetailDTO toDTO(){
        return new OrdenDetailDTO(this);
    }


}
