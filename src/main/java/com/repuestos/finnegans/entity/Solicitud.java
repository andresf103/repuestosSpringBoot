package com.repuestos.finnegans.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.finnegans.dto.SolicitudDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.FieldValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="solicitud")
public class Solicitud implements Serializable {
    @Id
    @NotNull

    private Long transactionId;
    private Long numeroInterno;
    @Column(length=500)
    private String descripcion;
    @Column(length=50)
    private String nombreUsuarioAlta;

    public Solicitud(SolicitudDTO solicitudDTO){
        valueOf(solicitudDTO);
    }

    public SolicitudDTO toDTO(){
        SolicitudDTO dto=new SolicitudDTO();
        dto.setDescripcion(descripcion);
        dto.setNombreUsuarioAlta(nombreUsuarioAlta);
        dto.setNumeroInterno(numeroInterno);
        dto.setTransactionId(transactionId);
        return dto;
    }

    public void valueOf(SolicitudDTO solicitudDTO){
        transactionId=solicitudDTO.getTransactionId();
        numeroInterno=solicitudDTO.getNumeroInterno();
        descripcion=solicitudDTO.getDescripcion();
        nombreUsuarioAlta=solicitudDTO.getNombreUsuarioAlta();
    }
}
