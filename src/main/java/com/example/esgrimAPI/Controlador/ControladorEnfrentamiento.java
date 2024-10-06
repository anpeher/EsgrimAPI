package com.example.esgrimAPI.Controlador;


import com.example.esgrimAPI.DTO.Enfrentamiento.EnfrentamientoResultadoDTO;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Servicio.ServicioEnfrentamiento.ServicioEnfrentamientoApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enfrentamiento")
@AllArgsConstructor
public class ControladorEnfrentamiento {

    ServicioEnfrentamientoApl servicioEnfrentamientoApl;

    @PostMapping("/{idEnfrentamiento}")
    public ResponseEntity resultadoEnfrentamiento(@PathVariable("idEnfrentamiento") Long id, @Valid @RequestBody EnfrentamientoResultadoDTO enfrentamientoResultadoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return new ResponseEntity<>(servicioEnfrentamientoApl.resultadoEnfrentamiento(id, enfrentamientoResultadoDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idEnfrentamiento}")
    public ResponseEntity verEnfrentamiento(@PathVariable("idEnfrentamiento") Long id) {
        try {
            return new ResponseEntity<>(servicioEnfrentamientoApl.verEnfrentamiento(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
