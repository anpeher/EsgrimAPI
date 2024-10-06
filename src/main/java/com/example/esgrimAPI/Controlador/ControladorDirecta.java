package com.example.esgrimAPI.Controlador;

import com.example.esgrimAPI.DTO.Directa.ResultadoDirectaDTO;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Servicio.ServicioDirecta.ServicioDirectaApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/directa")
@AllArgsConstructor
public class ControladorDirecta {

    private final ServicioDirectaApl servicioDirectaApl;

    @PostMapping("/{idDirecta}")
    public ResponseEntity resultadoDirecta(@PathVariable("idDirecta") Long id, @Valid @RequestBody ResultadoDirectaDTO resultadoDirectaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return ResponseEntity.ok(servicioDirectaApl.resultadoDirecta(id, resultadoDirectaDTO));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("verDirecta/{idDirecta}")
    public ResponseEntity verDirecta(@PathVariable("idDirecta") Long id) {
        try {
            return ResponseEntity.ok(servicioDirectaApl.verDirecta(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
