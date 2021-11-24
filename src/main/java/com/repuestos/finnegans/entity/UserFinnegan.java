package com.repuestos.finnegans.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
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
}
