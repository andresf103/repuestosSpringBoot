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
public class SolicitudDTO implements Serializable {

    @JsonProperty("TRANSACCIONID")
    private Long transactionId;
    @JsonProperty("NUMEROINTERNO")
    private Long numeroInterno;
    @JsonProperty("DESCRIPCION")
    private String descripcion;
    @JsonProperty("NOMBREUSUARIOALTA")
    private String nombreUsuarioAlta;
}
