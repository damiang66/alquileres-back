package com.alquiler.demo.repository;

import com.alquiler.demo.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol,Long> {
    Rol findByNombre(String nombre);
}
