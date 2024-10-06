package com.example.esgrimAPI.Controlador;

import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionDTO;
import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionGrupalDTO;
import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionSMS;
import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionSMSGrupal;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Excepciones.SmsException;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Servicio.ServicioNotificacion.ServicioNotificacionApl;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacion")
@AllArgsConstructor
public class ControladorNotificacion {

    ServicioNotificacionApl servicioNotificacionApl;

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity enviarNotificacionUsuario(@PathVariable("idUsuario") Long id, @Valid @RequestBody CrearNotificacionDTO crearNotificacion, BindingResult bindingResult){
        System.out.println("Contenido del DTO: " + crearNotificacion.getContenido());
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {

            return new ResponseEntity<>(servicioNotificacionApl.crearNotificacionPersonal(crearNotificacion, id), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (MessagingException e){
            return new ResponseEntity<>(new Message("error al enviar el correo"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity enviarNotificacionGrupo(@Valid @RequestBody CrearNotificacionGrupalDTO crearNotificacionGrupal, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            List<Usuario> usuarios = servicioNotificacionApl.crearNotificacionGrupal(crearNotificacionGrupal);
            if(usuarios.isEmpty()){return ResponseEntity.noContent().build();}
            return ResponseEntity.ok(usuarios);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (MessagingException e){
            return new ResponseEntity<>(new Message("error al enviar el correo"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clase/{idClase}")
    public ResponseEntity enviarNotificacionClase(@PathVariable("idClase") Long id, @Valid @RequestBody CrearNotificacionDTO crearNotificacionGrupal, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            List<Usuario> usuarios = servicioNotificacionApl.crearNotificacionClase(crearNotificacionGrupal, id);
            if(usuarios.isEmpty()){return ResponseEntity.noContent().build();}
            return ResponseEntity.ok(usuarios);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (MessagingException e){
            return new ResponseEntity<>(new Message("error al enviar el correo"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/rol")
    public ResponseEntity enviarNotificacionRol(@Valid @RequestBody CrearNotificacionGrupalDTO crearNotificacionGrupal, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            List<Usuario> usuarios = servicioNotificacionApl.crearNotificacionRol(crearNotificacionGrupal);
            if(usuarios.isEmpty()){return ResponseEntity.noContent().build();}
            return ResponseEntity.ok(usuarios);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (MessagingException e){
            return new ResponseEntity<>(new Message("error al enviar el correo"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sms")
    public ResponseEntity enviarNotificacionSMSUsuario(@Valid @RequestBody CrearNotificacionSMS crearNotificacionSMS, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            servicioNotificacionApl.crearNotificacionSMS(crearNotificacionSMS);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException | SmsException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuariosSMS")
    public ResponseEntity enviarNotificacionSMSGrupo(@Valid @RequestBody CrearNotificacionSMSGrupal crearNotificacionSMSGrupal, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            List<Usuario> usuarios = servicioNotificacionApl.crearNotificacionSMSGrupal(crearNotificacionSMSGrupal);
            if(usuarios.isEmpty()){return ResponseEntity.noContent().build();}
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (SmsException e){
            return new ResponseEntity<>(new Message("error al enviar el correo"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/notificaciones")
    public ResponseEntity verNotificaciones(){
        return ResponseEntity.ok(servicioNotificacionApl.verNotificaciones());
    }

    @GetMapping("/{idNotificacion}")
    public ResponseEntity verNotificacion(@PathVariable("idNotificacion") Long id){
        try {
            return ResponseEntity.ok(servicioNotificacionApl.verNotificacion(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
