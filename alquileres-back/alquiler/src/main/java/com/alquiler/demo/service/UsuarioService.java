package com.alquiler.demo.service;

import com.alquiler.demo.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario>findAll();
    Optional<Usuario>findById(Long id);
    Optional<Usuario>findByUsernameAndPassword(String username, String password);
    Usuario save(Usuario usuario);
    void deleteById(Long id);

}
