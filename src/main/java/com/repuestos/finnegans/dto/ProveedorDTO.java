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
public class ProveedorDTO implements Serializable {

    @JsonProperty("ORGANIZACIONID")
    private String cuit;
    @JsonProperty("ORGANIZACION")
    private String nombre;
    @JsonProperty("EMAIL")
    private String email;
}
