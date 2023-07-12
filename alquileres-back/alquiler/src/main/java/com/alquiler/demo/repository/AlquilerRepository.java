package com.alquiler.demo.repository;

import com.alquiler.demo.entity.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    @Query ("select a from Alquiler a where a.peticion.propiedad.propietario.id = ?1")
    public List<Alquiler> registroAlquiler(Long id);

    @Query ("select a from Alquiler a where a.peticion.propiedad.propietario.id = ?1 and aceptarPeticion = false")
    public List<Alquiler> aceptarPeticion(Long id);

}
