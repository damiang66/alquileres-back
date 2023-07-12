package com.alquiler.demo.controller;

import com.alquiler.demo.entity.Alquiler;
import com.alquiler.demo.entity.Propiedad;
import com.alquiler.demo.entity.Usuario;
import com.alquiler.demo.repository.PropiedadRepository;
import com.alquiler.demo.service.FotoService;
import com.alquiler.demo.service.PropiedadService;
import com.alquiler.demo.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/propiedad")
public class PropiedadController {

    private final Logger log = LoggerFactory.getLogger(PropiedadController.class);

    @Autowired
    private PropiedadService propiedadService;
    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FotoService fotoService;

    @GetMapping
    public ResponseEntity<?> findAll(){

        List<Propiedad> propiedades = propiedadService.findAll();

        if (propiedades.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(propiedadService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        Optional<Propiedad> optional = propiedadService.findById(id);

        if (optional.isPresent()){
            return ResponseEntity.ok().body(propiedadService.findById(id));
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Propiedad propiedad, BindingResult result ){
        System.out.println(propiedad);
        if (result.hasErrors()){
            return validation(result);
        }

        Optional<Usuario> optional = service.findById(propiedad.getPropietario().getId());
        Usuario usuario=null;
        if(optional.isPresent()){
           usuario = optional.get();
        }
 
        propiedad.setPropietario(usuario);


        return ResponseEntity.status(HttpStatus.CREATED).body(propiedadService.save(propiedad));

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Propiedad propiedad, BindingResult result){
        Optional<Propiedad> optional = propiedadService.findById(id);


        if (result.hasErrors()){
            return validation(result);
        }

        if (optional.isPresent()){
            Usuario usuario= null;
            Optional<Usuario> optionalUsuario = service.findById(propiedad.getPropietario().getId());
            if (optionalUsuario.isPresent()){
                usuario = optionalUsuario.get();
            }

            Propiedad propiedad1 = optional.get();

            propiedad1.setPropietario(usuario);
            propiedad1.setUbicacion(propiedad.getUbicacion());
            propiedad1.setFotos(propiedad.getFotos());
            propiedad1.setPrecio(propiedad.getPrecio());

           // propiedad1.setListaCliente(propiedad.getListaCliente());

            /*propiedad1.setListaCliente(propiedad.getListaCliente());*/


            return ResponseEntity.status(HttpStatus.CREATED).body(propiedadService.save(propiedad1));
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Optional<Propiedad> optional = propiedadService.findById(id);

        if (optional.isPresent()){
            propiedadService.deleteById(id);
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach( err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }


    @PostMapping("img/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        Map<String, Object> respuesta = new HashMap<>();

        Optional<Propiedad> propiedadOptional = propiedadService.findById(id);

        Propiedad  propiedad= propiedadOptional.get();

        if (!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                nombreArchivo = fotoService.copy(archivo);
            } catch (IOException e) {

                respuesta.put("error", e.getMessage() + " ");
                respuesta.put("mensaje", "error al cargar la foto");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
            }





            if (propiedad.getFotos()==null){
                propiedad.addFotosNull(nombreArchivo);
            }else {
                propiedad.addFotos(nombreArchivo);
            }





            propiedadService.save(propiedad);
            respuesta.put("propiedad", propiedad);
            respuesta.put("mensaje", "Ha subido correctamente la imagen" + nombreArchivo);

        }


        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }


    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

        Resource recurso = null;

        try {
            recurso = fotoService.upload(nombreFoto);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders cabecera = new HttpHeaders();

        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

}
