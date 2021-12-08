package com.repuestos.finnegans.entity;


import com.repuestos.finnegans.dto.OrdenDTO;
import com.repuestos.finnegans.dto.TipoDocumento;
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
    private Long transaccionId;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    private String empresa;
    private Long transaccionIdInicial;
    private String comprobante;

    public Orden(OrdenDTO ordenDTO){
        valueOf(ordenDTO);
    }

    public OrdenDTO toDto(){
        OrdenDTO dto=new OrdenDTO();
        dto.setComprobante(comprobante);
        dto.setEmpresa(empresa);
        dto.setTipoDocumento(tipoDocumento.getDocumento());
        dto.setTransaccionId(transaccionId);
        dto.setTransaccionIdInicial(transaccionIdInicial);
        return dto;
    }

    public void valueOf(OrdenDTO ordenDTO){
        comprobante=ordenDTO.getComprobante();
        transaccionId=ordenDTO.getTransaccionId();
        tipoDocumento=TipoDocumento.value(ordenDTO.getTipoDocumento());
        empresa=ordenDTO.getEmpresa();
        transaccionIdInicial=ordenDTO.getTransaccionIdInicial();
    }
}
