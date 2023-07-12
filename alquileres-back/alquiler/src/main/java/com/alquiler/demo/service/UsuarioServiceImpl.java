package com.alquiler.demo.service;

import com.alquiler.demo.entity.Rol;
import com.alquiler.demo.entity.Usuario;
import com.alquiler.demo.repository.RolRepositorio;
import com.alquiler.demo.repository.UsuarioRepositirio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepositirio repositirio;
    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public List<Usuario> findAll() {
        return repositirio.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repositirio.findById(id);
    }

    @Override
    public Optional<Usuario> findByUsernameAndPassword(String username, String password) {
        return repositirio.findByUsernameAndPassword(username, password);
    }

    @Override
    public Usuario save(Usuario usuario) {
    /*Rol rol = rolRepositorio.findByNombre("ROLE_USER");
    usuario.setRoles(Arrays.asList(rol));*/
        return repositirio.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        repositirio.deleteById(id);
    }
}
