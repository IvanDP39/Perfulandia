package com.microservice.usuario.controller;

import com.microservice.usuario.model.Usuario;
import com.microservice.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.net.URI;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioservice;

    //Localhost: 8090/api/v1/usuario/Listar 
    @GetMapping("L/listat")
    public List<Usuario>getAllUsuarios(){
        return usuarioservice.findAll();
    }
    
    //Localhost: 8090/api/v1/pacientes/1
    @GetMapping("/{id_usuario}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id_usuario){

        Optional<Usuario> usuario = usuarioservice.getUsuarioById(id_usuario);

        if(usuario.isPresent()){

            return ResponseEntity.ok()
                    .header("mi-encabezado", "valor")
                    .body(usuario.get());
        
        }   
        else{
            Map<String,String> errorBody = new HashMap<>();
            errorBody.put("message","No se emcontro el usuario con ese Id: " + id_usuario);
            errorBody.put("status", "404");
            errorBody.put("timestamp",LocalDateTime.now().toString());

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorBody);
        }

    }

    // Para guardar usuario
    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Usuario usuario){
        try{
            Usuario usuarioGuardado = usuarioservice.save(usuario);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(usuarioGuardado.getId_usuario())
                    .toUri();
            return ResponseEntity
                    .created(location)
                    .body(usuarioGuardado);
        } catch(DataIntegrityViolationException e){
            Map<String,String> error = new HashMap<>();
            error.put("message", "El correo ya esta registrado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    
    }

    //Localhost: 8090/api/v1/pacientes/editar/
    @PostMapping("/editar/{id_usuario}")
    public ResponseEntity<Usuario> update(@PathVariable int id_usuario,@RequestBody Usuario usuario){
        try{

            Usuario usu = usuarioservice.getUsuarioById2(id_usuario);
            usu.setId_usuario(id_usuario);
            usu.setNombre(usuario.getNombre());
            usu.setApellidos(usuario.getApellidos());
            usu.setRut(usuario.getRut());
            usu.setEdad(usuario.getEdad());
            usu.setCorreo(usuario.getCorreo());

            usuarioservice.save(usuario);
            return ResponseEntity.ok(usuario);
            
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    //Localhost: 8090/api/v1/pacientes/eliminar/
    @DeleteMapping("/(id_usuario)")
    public ResponseEntity<?> eliminar(@PathVariable int id_usuario){
        try{

            usuarioservice.delete(id_usuario);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }


}