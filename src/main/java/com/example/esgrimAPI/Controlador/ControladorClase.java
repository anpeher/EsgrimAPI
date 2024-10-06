package com.example.esgrimAPI.Controlador;

import com.example.esgrimAPI.DTO.Clase.CrearClaseDTO;
import com.example.esgrimAPI.DTO.Clase.ModClaseDTO;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Servicio.ServicioClase.ServicioClaseApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clase")
@AllArgsConstructor
public class ControladorClase {

    private final ServicioClaseApl servicioClaseApl;

    @PostMapping
    public ResponseEntity generarClase(@Valid @RequestBody CrearClaseDTO crearClaseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return new ResponseEntity<>(servicioClaseApl.crearClase(crearClaseDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idClase}")
    public ResponseEntity modificarClase(@PathVariable("idClase") Long id, @Valid @RequestBody ModClaseDTO modClaseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return ResponseEntity.ok(servicioClaseApl.modClase(id, modClaseDTO));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idClase}")
    public ResponseEntity obtenerClase(@PathVariable("idClase") Long id) {
        try {
            return ResponseEntity.ok(servicioClaseApl.verClase(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idClase}")
    public ResponseEntity eliminarClase(@PathVariable("idClase") Long id) {
        try {
            servicioClaseApl.eliminarClase(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity obtenerUsuariosClases(@PathVariable("idUsuario") Long id) {
        try {
            List<Usuario> usuarios = servicioClaseApl.UsuariosInscritos(id);
            if (usuarios.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(usuarios);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
