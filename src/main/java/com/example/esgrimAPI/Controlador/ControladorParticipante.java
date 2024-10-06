package com.example.esgrimAPI.Controlador;


import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.DTO.Participante.CrearParticipanteDTO;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Servicio.ServicioParticipante.ServicioParticipanteApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participante")
@AllArgsConstructor
public class ControladorParticipante {

    private final ServicioParticipanteApl servicioParticipanteApl;

    @PostMapping
    public ResponseEntity generarParticipante(@Valid @RequestBody CrearParticipanteDTO crearParticipanteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return new ResponseEntity<>(servicioParticipanteApl.ApuntarseTorneo(crearParticipanteDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{idParticipante}")
    public ResponseEntity obtenerParticipante(@PathVariable("idParticipante") Long id) {
        try {
            return ResponseEntity.ok(servicioParticipanteApl.verParticipante(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idParticipante}")
    public ResponseEntity descalificarParticipante(@PathVariable("idParticipante") Long id) {
        try {
            servicioParticipanteApl.descalificarParticipante(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idParticipante}/readmitir")
    public ResponseEntity readmitirParticipante(@PathVariable("idParticipante") Long id) {
        try {
            servicioParticipanteApl.readmitirParticipante(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
