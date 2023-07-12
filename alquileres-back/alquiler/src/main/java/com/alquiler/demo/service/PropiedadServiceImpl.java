package com.alquiler.demo.service;

import com.alquiler.demo.entity.Propiedad;
import com.alquiler.demo.repository.PropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropiedadServiceImpl implements PropiedadService{

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Override
    public List<Propiedad> findAll() {
        return propiedadRepository.findAll();
    }

    @Override
    public Optional<Propiedad> findById(Long id) {
        return propiedadRepository.findById(id);
    }

    @Override
    public Propiedad save(Propiedad propiedad) {
        return propiedadRepository.save(propiedad);
    }

    @Override
    public void deleteById(Long id) {
        propiedadRepository.deleteById(id);
    }
}
