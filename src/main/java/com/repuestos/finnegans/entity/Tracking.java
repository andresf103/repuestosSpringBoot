package com.repuestos.finnegans.entity;


import com.repuestos.finnegans.dto.TrackingDTO;
import com.repuestos.finnegans.dto.TipoDocumento;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="tracking")
public class Tracking implements Serializable {

    @Id
    @NotNull
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    private String empresa;

    private Long transactionIdInicial;
    private String comprobante;
    private String origen;
    private String numero;

    public Tracking(TrackingDTO trackingDTO){
        valueOf(trackingDTO);
    }

    public TrackingDTO toDto(){
        TrackingDTO dto=new TrackingDTO();
        dto.setComprobante(comprobante);
        dto.setEmpresa(empresa);
        dto.setTipoDocumento(tipoDocumento.getDocumento());
        dto.setTransactionId(transactionId);
        dto.setTransactionIdInicial(transactionIdInicial);
        dto.setNumero(numero);
        dto.setOrigen(origen);
        return dto;
    }

    public void valueOf(TrackingDTO trackingDTO){
        comprobante= trackingDTO.getComprobante();
        transactionId= trackingDTO.getTransactionId();
        tipoDocumento=TipoDocumento.value(trackingDTO.getTipoDocumento());
        empresa= trackingDTO.getEmpresa();
        transactionIdInicial= trackingDTO.getTransactionIdInicial();
        origen= trackingDTO.getOrigen();
        numero= trackingDTO.getNumero();
    }
}
