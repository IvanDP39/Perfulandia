package com.microservice.usuario.service;

import com.microservice.usuario.model.Usuario;
import com.microservice.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(int id_usuario){
        return usuarioRepository.findById(id_usuario);
    }

    public Usuario getUsuarioById2(int id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void delete(int id_usuario){
        usuarioRepository.deleteById(id_usuario);
    }

}
