package com.example.esgrimAPI.Controlador;

import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Servicio.ServicioPoule.ServicioPoule;
import com.example.esgrimAPI.Servicio.ServicioPoule.ServicioPouleApl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poule")
@AllArgsConstructor
public class ControladorPoule {

    private final ServicioPouleApl servicioPouleApl;

    @GetMapping("/{idPoule}")
    public ResponseEntity obtenerParticipantes(@PathVariable("idPoule") Long id) {
        try {
            return ResponseEntity.ok(servicioPouleApl.verParticipantes(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("IniciarEnfrentamientos/{idPoule}")
    public ResponseEntity crearEnfrentamientos(@PathVariable("idPoule") Long id) {
        try {
            return ResponseEntity.ok(servicioPouleApl.CrearEnfrentamientos(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("verEnfrentamientos/{idPoule}")
    public ResponseEntity verEnfrentamientos(@PathVariable("idPoule") Long id) {
        try {
            return ResponseEntity.ok(servicioPouleApl.verEnfrentamientosPoule(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("verResultados/{idPoule}")
    public ResponseEntity verResultados(@PathVariable("idPoule") Long id) {
        try {
            return ResponseEntity.ok(servicioPouleApl.verResultadoPoule(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
