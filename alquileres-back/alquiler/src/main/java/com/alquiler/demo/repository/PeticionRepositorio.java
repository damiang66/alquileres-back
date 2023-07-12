package com.alquiler.demo.repository;

import com.alquiler.demo.entity.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeticionRepositorio extends JpaRepository<Peticion,Long> {
    @Query("select p from Peticion p where p.propiedad.id=?1")
    public List<Peticion>findByPropiedadId(Long id);
}
