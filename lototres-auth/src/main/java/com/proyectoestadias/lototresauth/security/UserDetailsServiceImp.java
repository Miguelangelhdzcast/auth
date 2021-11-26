package com.proyectoestadias.lototresauth.security;

import com.proyectoestadias.lototresauth.entity.Usuario;
import com.proyectoestadias.lototresauth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByEmail(email).orElseThrow(()->new UsernameNotFoundException("El email no esta registrado"));
        return UsuarioPrincipalFactory.build(usuario);
    }
}
