package com.alquiler.demo.service;

import com.alquiler.demo.entity.Alquiler;
import com.alquiler.demo.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlquilerSeviceImpl implements AlquilerService{

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Override
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public Optional<Alquiler> findById(Long id) {
        return alquilerRepository.findById(id);
    }

    @Override
    public List<Alquiler> findByPropietario(Long idPropietario) {
        return alquilerRepository.registroAlquiler(idPropietario);
    }

    @Override
    public Alquiler save(Alquiler alquiler) {
        alquiler.setAceptarPeticion(false);
        return alquilerRepository.save(alquiler);
    }

    @Override
    public void deleteById(Long id) {
        alquilerRepository.deleteById(id);
    }
}
