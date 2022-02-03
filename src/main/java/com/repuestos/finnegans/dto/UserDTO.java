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
public class UserDTO implements Serializable {

    @JsonProperty("NOMBRE")
    private String nombre;
    @JsonProperty("CODIGO")
    private String codigo;
    @JsonProperty("DESCRIPCION")
    private String descripcion;
    @JsonProperty("ACTIVO")
    private String activo;
    @JsonProperty("FECHAEXPIRACION")
    private String fechaExpiracion;
    @JsonProperty("EMAIL")
    private String email;
}
