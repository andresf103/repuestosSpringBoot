package com.repuestos.finnegans.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrdenDTO implements Serializable {

    @JsonProperty("TRANSACCIONID")
    private Long transaccionId;
    @JsonProperty("TIPO_DOCUMENTO")
    private String tipoDocumento;
    @JsonProperty("ORGANIZACION")
    private String empresa;
    @JsonProperty("TRANSACCIONIDINICIAL")
    private Long transaccionIdInicial;
    @JsonProperty("COMPROBANTE")
    private String comprobante;

}
