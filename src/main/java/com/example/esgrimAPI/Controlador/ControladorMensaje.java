package com.example.esgrimAPI.Controlador;

import com.example.esgrimAPI.DTO.Mensaje.CrearMensajeDTO;
import com.example.esgrimAPI.DTO.Mensaje.ModMensajeDTO;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Mensaje.Mensaje;
import com.example.esgrimAPI.Servicio.ServicioMensaje.ServicioMensajeApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mensaje")
@AllArgsConstructor
public class ControladorMensaje {

    private final ServicioMensajeApl servicioMensajeApl;

    @PostMapping
    public ResponseEntity generarMensaje(@Valid @RequestBody CrearMensajeDTO crearMensajeDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try{
            return new ResponseEntity<>(servicioMensajeApl.crearMensaje(crearMensajeDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idMensaje}")
    public ResponseEntity modificarMensaje(@PathVariable("idMensaje") Long id, @Valid @RequestBody ModMensajeDTO modMensajeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return ResponseEntity.ok(servicioMensajeApl.modMensaje(modMensajeDTO,id));
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idMensaje}")
    public ResponseEntity obtenerMensaje(@PathVariable("idMensaje") Long id){
        try {
            return ResponseEntity.ok(servicioMensajeApl.verMensaje(id));
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idMensaje}")
    public ResponseEntity eliminarMensajes(@PathVariable("idMensaje") Long id){
        try {
            servicioMensajeApl.eliminarMensaje(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity obtenerMensajesUsuarios(@PathVariable("idUsuario") Long id){
        try {
            List<Mensaje> mensajes = servicioMensajeApl.mensajesUsuario(id);
            if(mensajes.isEmpty()){return ResponseEntity.noContent().build();}
            return ResponseEntity.ok(mensajes);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }





}
