package com.alquiler.demo.controller;

import com.alquiler.demo.entity.Peticion;
import com.alquiler.demo.service.PeticionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin ("*")
@RequestMapping("/peticion")
public class PeticionControlador {


    @Autowired
    private PeticionService peticionService;

    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "El campo: " + e.getField() + " " + e.getDefaultMessage());
        });
        // return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
        return ResponseEntity.badRequest().body(errores);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Peticion> lista = new ArrayList<>();
        lista = peticionService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<?> findByPropiedadId(@PathVariable Long idPropiedad) {
        List<Peticion> lista = new ArrayList<>();
        lista = peticionService.findByPropiedadId(idPropiedad);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<Peticion>optional= peticionService.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok().body(optional.get());
        }
    return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?>save(@Valid @RequestBody Peticion peticion,BindingResult result){
        if(result.hasErrors()){
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(peticionService.save(peticion));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteById(@PathVariable Long id){
        Optional<Peticion>optional = peticionService.findById(id);
        if (optional.isPresent()){
            peticionService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
