package com.repuestos.finnegans.entity;

import com.repuestos.finnegans.dto.ProveedorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="proveedor")
public class Proveedor implements Serializable {

    @Id
    @Column(name="cuit", nullable=false)
    @NotBlank
    private String cuit;
    private String nombre;
    private String email;
    private boolean enviar=false;

    public Proveedor(ProveedorDTO proveedorDTO){
        valueOf(proveedorDTO);
    }

    public ProveedorDTO toDTO(){
        ProveedorDTO dto=new ProveedorDTO();
        dto.setCuit(cuit);
        dto.setNombre(nombre);
        dto.setEmail(email);
        return dto;
    }

    public void valueOf(ProveedorDTO proveedorDTO){
        this.cuit=proveedorDTO.getCuit();
        this.email=proveedorDTO.getEmail();
        this.nombre=proveedorDTO.getNombre();
    }

}
