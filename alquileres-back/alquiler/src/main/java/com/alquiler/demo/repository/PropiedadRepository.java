package com.alquiler.demo.repository;

import com.alquiler.demo.entity.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {

}
