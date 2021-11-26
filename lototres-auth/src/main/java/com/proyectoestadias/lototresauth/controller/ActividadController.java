package com.proyectoestadias.lototresauth.controller;

import com.proyectoestadias.lototresauth.entity.Actividad;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actividad")
@CrossOrigin
public class ActividadController {

    ArrayList actividades = new ArrayList(){{
       add(new Actividad("actividad1", 11, 10 ));
       add(new Actividad("actividad2", 12, 11 ));
       add(new Actividad("actividad3", 12, 12 ));
       add(new Actividad("actividad4", 12, 12 ));
    }};

    @GetMapping("/lista")
    public ResponseEntity<List<Actividad>> lista(){
         return new ResponseEntity<>(actividades, HttpStatus.OK);
    }

}
