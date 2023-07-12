package com.alquiler.demo.service;


import com.alquiler.demo.entity.Propiedad;

import java.util.List;
import java.util.Optional;


public interface PropiedadService {

    public List<Propiedad> findAll();

    public Optional<Propiedad> findById(Long id);

    public Propiedad save(Propiedad propiedad);

    public void deleteById(Long id);
}
