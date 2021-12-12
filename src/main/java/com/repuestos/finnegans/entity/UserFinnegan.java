package com.repuestos.finnegans.entity;

import com.repuestos.finnegans.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name="user_finnegan")
public class UserFinnegan implements Serializable {
    @Id
    @Column(name="id_user", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String email;

    public UserFinnegan(UserDTO userDTO){
        this.nombre=userDTO.getNombre();
        this.email=userDTO.getEmail();
    }
}
