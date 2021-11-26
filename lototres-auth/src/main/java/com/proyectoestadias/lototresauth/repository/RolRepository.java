package com.proyectoestadias.lototresauth.repository;

import com.proyectoestadias.lototresauth.entity.Rol;
import com.proyectoestadias.lototresauth.entity.Usuario;
import com.proyectoestadias.lototresauth.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    boolean existsByRolNombre(RolNombre rolNombre);
}
