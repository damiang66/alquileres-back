package com.alquiler.demo.service;

import com.alquiler.demo.entity.Alquiler;

import java.util.List;
import java.util.Optional;

public interface AlquilerService {

    public List<Alquiler> findAll();

    public Optional<Alquiler> findById(Long id);
    public List<Alquiler>findByPropietario(Long idPropietario);

    public Alquiler save(Alquiler alquiler);

    public void deleteById(Long id);
}
