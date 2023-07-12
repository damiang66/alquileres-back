package com.alquiler.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;



import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "propiedades")
public class Propiedad {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JoinColumn(name = "propietarios")
    private Usuario propietario;

    @NotBlank
    private String ubicacion;





    private List<String> fotos ;




    @NotNull
    private Double precio;



    public Propiedad() {
        this.fotos = new ArrayList<>();

    //    this.propietario= new Usuario();
    }





    public void addFotosNull(String foto){
        this.fotos = new ArrayList<>();
        this.fotos.add(foto);
    }

    public void addFotos(String foto){
        this.fotos.add(foto);

    }
}


