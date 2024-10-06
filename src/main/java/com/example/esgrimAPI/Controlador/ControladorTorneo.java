package com.example.esgrimAPI.Controlador;


import com.example.esgrimAPI.DTO.Torneo.CrearTorneoDTO;
import com.example.esgrimAPI.DTO.Torneo.ModTorneoDTO;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Servicio.ServicioTorneo.ServicioTorneoApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/torneo")
@AllArgsConstructor
public class ControladorTorneo {
    private final ServicioTorneoApl servicioTorneoApl;

    @PostMapping
    public ResponseEntity generarTorneo(@Valid @RequestBody CrearTorneoDTO crearTorneoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return new ResponseEntity<>(servicioTorneoApl.crearTorneo(crearTorneoDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idTorneo}")
    public ResponseEntity modificarTorneo(@PathVariable("idTorneo") Long id, @Valid @RequestBody ModTorneoDTO modTorneoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return ResponseEntity.ok(servicioTorneoApl.modTorneo(id, modTorneoDTO));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idTorneo}")
    public ResponseEntity obtenerTorneo(@PathVariable("idTorneo") Long id) {
        try {
            return ResponseEntity.ok(servicioTorneoApl.verTorneo(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idTorneo}/participantesIniciales")
    public ResponseEntity obtenerTParticipantesOrdenados(@PathVariable("idTorneo") Long id) {
        try {
            return ResponseEntity.ok(servicioTorneoApl.participantesTorneoClasificacionInicial(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idTorneo}/inicializarPoules")
    public ResponseEntity inicializarPoules(@PathVariable("idTorneo") Long id) {
        try {
            return ResponseEntity.ok(servicioTorneoApl.InicializarTorneo(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idTorneo}/resultadoPoules")
    public ResponseEntity finalizarPoules(@PathVariable("idTorneo") Long id) {
        try {
            return ResponseEntity.ok(servicioTorneoApl.finalizarPoules(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idTorneo}/clasificacionPoules")
    public ResponseEntity clasificacionPoules(@PathVariable("idTorneo") Long id) {
        try {
            return ResponseEntity.ok(servicioTorneoApl.clasificacionPostPoules(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idTorneo}/clasificacion")
    public ResponseEntity verclasificacion(@PathVariable("idTorneo") Long id) {
        try {
            return ResponseEntity.ok(servicioTorneoApl.clasificacionParticipantes(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idTorneo}/ronda")
    public ResponseEntity RondaDirectas(@PathVariable("idTorneo") Long id) {
        try {
            return ResponseEntity.ok(servicioTorneoApl.rondaDirectas(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idTorneo}/directas")
    public ResponseEntity verDirectas(@PathVariable("idTorneo") Long id) {
        try {
            return ResponseEntity.ok(servicioTorneoApl.directasTorneo(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idTorneo}/finalizarTorneo")
    public ResponseEntity finalizarTorneo(@PathVariable("idTorneo") Long id) {
        try {
            return ResponseEntity.ok(servicioTorneoApl.terminarTorneo(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idTorneo}")
    public ResponseEntity eliminarTorneo(@PathVariable("idTorneo") Long id) {
        try {
            servicioTorneoApl.cerrarTorneo(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
