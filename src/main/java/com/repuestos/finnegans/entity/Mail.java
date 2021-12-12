package com.repuestos.finnegans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Mail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmail;
    private Instant created;
    private Instant sendDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    private Orden orden;

    public Mail(Orden orden){
        created=new Date().toInstant();
        status=Status.NEW;
        this.orden=orden;
    }
}

