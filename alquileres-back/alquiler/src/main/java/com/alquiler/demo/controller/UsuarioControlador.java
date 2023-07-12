package com.alquiler.demo.controller;

import com.alquiler.demo.entity.Usuario;
import com.alquiler.demo.service.FotoService;
import com.alquiler.demo.service.FotoServiceImpl;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioService service;

    @Autowired
    private FotoService fotoService;

    private final Logger log = LoggerFactory.getLogger(UsuarioControlador.class);


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
        });
        return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
    }


    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Usuario> o = service.findById(id);
        if (o.isPresent()) {
            return ResponseEntity.ok().body(o.get());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/nombre/{username}/{password}")
    public ResponseEntity<?> findByUsername(@PathVariable String username, @PathVariable String password) {
        Optional<Usuario> o = service.findByUsernameAndPassword(username, password);
        if (o.isPresent()) {
            return ResponseEntity.ok().body(o.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return this.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return this.validation(result);
        }

        Optional<Usuario> o = service.findById(id);
        if (o.isPresent()) {

            Usuario usuarioDb = o.get();

            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setApellido(usuario.getApellido());
            usuarioDb.setFoto(usuario.getFoto());

            System.out.println(usuarioDb);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        Optional<Usuario>o = service.findById(id);
        if (o.isPresent()){
            fotoService.delete(o.get().getFoto());
            service.deleteById(id);

            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }


    @PostMapping("foto/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo , @RequestParam("id") Long id){
        Map<String,Object> respuesta = new HashMap<>();

        Optional<Usuario> usuarioOptional = service.findById(id);

        Usuario usuario = usuarioOptional.get();

        if (!archivo.isEmpty()){
            String nombreArchivo = null;
            try {
                nombreArchivo=fotoService.copy(archivo);
            } catch (IOException e) {

                respuesta.put("error",e.getMessage()+ " ");
                respuesta.put("mensaje", "error al cargar la foto de usuario");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
            }
            String nombreFotoAnterior = usuario.getFoto();
            fotoService.delete(nombreFotoAnterior);

            usuario.setFoto(nombreArchivo);
            service.save(usuario);
            respuesta.put("usuario", usuario);
            respuesta.put("mensaje", "Ha subido correctamente la imagen"+ nombreArchivo );

        }



        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }


    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

        Resource recurso = null;

        try {
            recurso = fotoService.upload(nombreFoto);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders cabecera = new HttpHeaders();

        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() +  "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

}
