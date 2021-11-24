package com.repuestos.finnegans.entity;


import com.repuestos.finnegans.dto.OrdenDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="orden")
public class Orden implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaccionId;
    private String tipoDocumento;
    private String empresa;
    private Long transaccionIdInicial;
    private String comprobante;

    Orden(OrdenDTO ordenDTO){
        valueOf(ordenDTO);
    }

    public OrdenDTO toDto(){
        OrdenDTO dto=new OrdenDTO();
        dto.setComprobante(comprobante);
        dto.setEmpresa(empresa);
        dto.setTipoDocumento(tipoDocumento);
        dto.setTransaccionId(transaccionId);
        dto.setTransaccionIdInicial(transaccionIdInicial);
        return dto;
    }

    public void valueOf(OrdenDTO ordenDTO){
        comprobante=ordenDTO.getComprobante();
        transaccionId=ordenDTO.getTransaccionId();
        tipoDocumento=ordenDTO.getTipoDocumento();
        empresa=ordenDTO.getEmpresa();
        transaccionIdInicial=ordenDTO.getTransaccionIdInicial();
    }
}
