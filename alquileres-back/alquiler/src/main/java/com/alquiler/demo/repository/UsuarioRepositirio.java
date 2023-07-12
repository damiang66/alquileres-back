package com.alquiler.demo.repository;

import com.alquiler.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositirio extends JpaRepository<Usuario,Long> {
    public Optional<Usuario>findByUsernameAndPassword(String username, String password);
}
