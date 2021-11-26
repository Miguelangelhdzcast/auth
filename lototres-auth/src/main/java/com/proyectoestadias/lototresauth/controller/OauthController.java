package com.proyectoestadias.lototresauth.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.proyectoestadias.lototresauth.dto.TokenDto;
import com.proyectoestadias.lototresauth.entity.Rol;
import com.proyectoestadias.lototresauth.entity.Usuario;
import com.proyectoestadias.lototresauth.enums.RolNombre;
import com.proyectoestadias.lototresauth.security.Jwt.JwtProvider;
import com.proyectoestadias.lototresauth.service.RolService;
import com.proyectoestadias.lototresauth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/oauth")
@CrossOrigin
public class OauthController {



    @Value("${google.clientId}")
    String googleclientId;

    @Value("${secretPsw}")
    String secrestPsw;

    @Autowired
     PasswordEncoder passwordEncoder;

     @Autowired
     AuthenticationManager authenticationManager;

    @Autowired
     JwtProvider jwtProvider;
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

     @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDto tokendto) throws IOException {
     final NetHttpTransport transport = new NetHttpTransport();
     final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
         GoogleIdTokenVerifier.Builder verifier =
                 new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                         .setAudience(Collections.singletonList(googleclientId));

     final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokendto.getValue());
     final GoogleIdToken.Payload payload = googleIdToken.getPayload();
     return new ResponseEntity<>(payload, HttpStatus.OK);


    }
    @PostMapping("/facebook")
    public ResponseEntity<?> facebook(@RequestBody TokenDto tokendto) throws IOException {
       Facebook facebook = new FacebookTemplate(tokendto.getValue());
       final String [] fields = {"email", "picture"};
       User user = facebook.fetchObject( "me" , User.class, fields);
       return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private TokenDto login(Usuario usuario){
         Authentication authentication 


    }
    private Usuario saveUsuario(String email){
         Usuario usuario = new Usuario (email, passwordEncoder.encode(secrestPsw));
        Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolUser);
        usuario.setRoles(roles);
        return usuarioService.save(usuario);
    }

}
