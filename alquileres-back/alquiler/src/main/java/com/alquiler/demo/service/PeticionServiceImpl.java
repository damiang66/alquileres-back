package com.alquiler.demo.service;

import com.alquiler.demo.entity.Peticion;
import com.alquiler.demo.entity.Propiedad;
import com.alquiler.demo.entity.Usuario;
import com.alquiler.demo.repository.PeticionRepositorio;
import com.alquiler.demo.repository.PropiedadRepository;
import com.alquiler.demo.repository.UsuarioRepositirio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeticionServiceImpl implements PeticionService {
    @Autowired
    private PeticionRepositorio peticionRepositorio;
    @Autowired
    private PropiedadRepository propiedadRepository;
    @Autowired
    private UsuarioRepositirio usuarioRepositirio;

    @Override
    public List<Peticion> findByPropiedadId(Long propiedadId) {
        return peticionRepositorio.findByPropiedadId(propiedadId);
    }

    @Override
    public List<Peticion> findAll() {
        return peticionRepositorio.findAll();
    }

    @Override
    public Optional<Peticion> findById(Long id) {
        return peticionRepositorio.findById(id);
    }

    @Override
    public Peticion save(Peticion peticion) {
        Propiedad propiedad= null;
        Usuario usuario = null;
        Optional<Usuario>optionalUsuario= usuarioRepositirio.findById(peticion.getCliente().getId());
        Optional<Propiedad>optionalPropiedad= propiedadRepository.findById(peticion.getPropiedad().getId());
        if(optionalPropiedad.isPresent() && optionalUsuario.isPresent()){
            propiedad= optionalPropiedad.get();
            usuario= optionalUsuario.get();
            peticion.setPropiedad(propiedad);
            peticion.setCliente(usuario);
            return peticionRepositorio.save(peticion);
        }
        return null;


    }

    @Override
    public void deleteById(Long id) {
    peticionRepositorio.deleteById(id);
    }
}
