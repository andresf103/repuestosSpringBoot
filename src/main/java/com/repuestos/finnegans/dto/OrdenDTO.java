package com.repuestos.finnegans.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdenDTO implements Serializable {

    @JsonProperty("TRANSACCIONID")
    private Long transactionId;
    @JsonProperty("DESCRIPCION")
    private String descripcion;
    @JsonProperty("ORGANIZACIONNOMBRE")
    private String proveedor;
    @JsonProperty("NUMERODOCUMENTO")
    private String numeroOrden;
    @JsonProperty("FECHA")
    private String fecha;
}
