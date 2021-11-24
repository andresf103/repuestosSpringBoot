package com.repuestos.finnegans.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.finnegans.dto.SolicitudDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="solicitud")
public class Solicitud implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Long numeroInterno;
    private String descripcion;
    private String nombreUsuarioAlta;

    Solicitud(SolicitudDTO solicitudDTO){
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
