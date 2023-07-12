package com.alquiler.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;

import java.util.List;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    public Usuario() {
        this.roles = new ArrayList<>();

    }

    public Usuario(Long id, String username, String nombre, String apellido, String dni, String telefono, String email, String password, String foto, List<Rol> roles) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.roles = roles;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;//
    @NotBlank
    private String nombre;//
    @NotBlank
    private String apellido;//
    @NotBlank
    private String dni;//
    @NotBlank
    private String telefono;//
    @NotBlank
    private String email;//
    @NotBlank
    private String password;
    private String foto;

    /*DOBLE REGISTRO DE SESION Y AHI SE LE AGREGA CLIENTE O PROPIETARIO*/

    @ManyToMany
    @JsonIgnoreProperties()
    private List<Rol> roles;

    @PrePersist
    private void rol(){
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setNombre("ROLE_USER");
        this.roles.add(rol);
    }



}
