package com.example.esgrimAPI.Controlador;

import com.example.esgrimAPI.DTO.Usuario.ContraseñaUsuario;
import com.example.esgrimAPI.DTO.Usuario.CrearUsuarioDTO;
import com.example.esgrimAPI.DTO.Usuario.ModUsuarioDTO;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.DTO.Usuario.RolUsuario;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Servicio.ServicioUsuario.ServicioUsuarioApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class ControladorUsuario {

    private final ServicioUsuarioApl servicioUsuarioApl;

    @PostMapping
    public ResponseEntity generarUsuario(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return new ResponseEntity<>(servicioUsuarioApl.crearUsuario(crearUsuarioDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity modificarUsuario(@PathVariable("idUsuario") Long id, @Valid @RequestBody ModUsuarioDTO modUsuarioDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return ResponseEntity.ok(servicioUsuarioApl.modUsuario(modUsuarioDTO, id));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity obtenerUsuario(@PathVariable("idUsuario") Long id) {
        try {
            return ResponseEntity.ok(servicioUsuarioApl.verUsuario(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity eliminarUsuario(@PathVariable("idUsuario") Long id) {
        try {
            servicioUsuarioApl.eliminarUsuario(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity obtenerUsuarios() {
        return ResponseEntity.ok(servicioUsuarioApl.verUsuarios());
    }

    @GetMapping("/clasificacion")
    public ResponseEntity obtenerClasificacionUsuarios() {
        return ResponseEntity.ok(servicioUsuarioApl.verUsuariosPorOrdenDeClasificacion());
    }

    @GetMapping("/reset")
    public ResponseEntity resetearPuntuacion() {
        servicioUsuarioApl.resetearPuntuacion();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/Clase/{idUsuario}/{idClase}")
    public ResponseEntity apuntarseClase(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idClase") Long idClase) {
        try {
            return ResponseEntity.ok(servicioUsuarioApl.apuntarseClase(idUsuario,idClase));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reactivar/{idUsuario}")
    public ResponseEntity reactivarUsuario(@PathVariable("idUsuario") Long id) {
        try{
            return ResponseEntity.ok(servicioUsuarioApl.reactivarUsuario(id));
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/rol/{idUsuario}")
    public ResponseEntity cambiarRol(@PathVariable("idUsuario") Long id, @Valid @RequestBody RolUsuario rolUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return new ResponseEntity<>(servicioUsuarioApl.cambiarRol(id, rolUsuario), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contrasena/{idUsuario}")
    public ResponseEntity cambiarRol(@PathVariable("idUsuario") Long id, @Valid @RequestBody ContraseñaUsuario contraseñaUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            servicioUsuarioApl.cambiarContrasena(id, contraseñaUsuario);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
