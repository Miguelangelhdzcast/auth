package com.proyectoestadias.lototresauth.service;

import com.proyectoestadias.lototresauth.entity.Usuario;
import com.proyectoestadias.lototresauth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByEmail(String email){
         return usuarioRepository.findByEmail(email);
    }
    public boolean existsEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);

    }
}
