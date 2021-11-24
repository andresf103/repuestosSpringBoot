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
//todas las llamadas se deben hacer a api.teamplace.finneg.com/api/
// https://2.teamplace.finneg.com/BSA/api/reports/tracking?
// domain=oic&PARAMWEBREPORT_FechaDesde=20211111
// &PARAMWEBREPORT_FechaHasta=20211111
// &PARAMWEBREPORT_Empresa=OICSA1
// &example=1&
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
