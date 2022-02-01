package com.repuestos.finnegans.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.finnegans.entity.OrdenDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrdenDetailDTO implements Serializable {

    @JsonProperty("TRANSACCIONID")
    private Long transactionId;
    @JsonProperty("DESCITEM")
    private String descItem;
    @JsonProperty("PRODUCTO")
    private String producto;
    @JsonProperty("CANTIDAD")
    private Long cantidad;
    @JsonProperty("PRECIO")
    private BigDecimal precio;


    public OrdenDetailDTO(OrdenDetail detail){
        transactionId= detail.getOrden().getTransactionId();
        descItem= detail.getDescripcion();
        producto= detail.getProducto();
        cantidad=detail.getCantidad();
        precio=detail.getPrecio();
    }
}
