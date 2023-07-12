package com.alquiler.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "peticiones")
public class Peticion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @NotNull
    private Propiedad propiedad;
    @OneToOne
    @NotNull
    private Usuario cliente;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @PrePersist
    public void prePersit(){
        this.fecha= new Date();

    }
}
