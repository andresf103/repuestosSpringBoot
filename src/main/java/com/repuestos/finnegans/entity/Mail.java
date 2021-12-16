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
    private Tracking tracking;

    public Mail(Tracking tracking){
        created=new Date().toInstant();
        status=Status.NEW;
        this.tracking = tracking;
    }
}

