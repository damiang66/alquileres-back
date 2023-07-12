package com.alquiler.demo.service;

import com.alquiler.demo.entity.Peticion;

import java.util.List;
import java.util.Optional;

public interface PeticionService {
    public List<Peticion>findByPropiedadId(Long propiedadId);
    public List<Peticion> findAll();
    public Optional<Peticion>findById(Long id);
    public Peticion save(Peticion peticion);
    public void deleteById(Long id);
}
