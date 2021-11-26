package com.proyectoestadias.lototresauth.service;

import com.proyectoestadias.lototresauth.entity.Rol;
import com.proyectoestadias.lototresauth.entity.Usuario;

import com.proyectoestadias.lototresauth.enums.RolNombre;
import com.proyectoestadias.lototresauth.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
    public boolean existsRolNombre(RolNombre rolNombre){
        return rolRepository.existsByRolNombre(rolNombre);
    }
    public void save(Rol rol){
        return rolRepository.save(rol);

    }

}
