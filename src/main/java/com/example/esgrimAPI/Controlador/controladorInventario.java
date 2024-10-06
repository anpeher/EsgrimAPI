package com.example.esgrimAPI.Controlador;


import com.example.esgrimAPI.DTO.Inventario.CrearItemDTO;
import com.example.esgrimAPI.DTO.Inventario.ModItemDTO;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Inventario.Inventario;
import com.example.esgrimAPI.Servicio.ServicioInventario.ServicioInventarioApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/inventario")
@AllArgsConstructor
public class controladorInventario {

    private final ServicioInventarioApl servicioInventarioApl;

    @PostMapping
    public ResponseEntity generarItem(@Valid @RequestBody CrearItemDTO crearItemDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return new ResponseEntity<>(servicioInventarioApl.crearItem(crearItemDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idItem}")
    public ResponseEntity modificarItem(@PathVariable("idItem") Long id, @Valid @RequestBody ModItemDTO modItemDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return ResponseEntity.ok(servicioInventarioApl.modItem(id, modItemDTO));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idItem}")
    public ResponseEntity obtenerItem(@PathVariable("idItem") Long id) {
        try {
            return ResponseEntity.ok(servicioInventarioApl.verItem(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/items")
    public ResponseEntity obtenerItems() {
        List<Inventario> items = servicioInventarioApl.items();
        if(items.isEmpty()){ ResponseEntity.noContent().build();}
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity eliminarItem(@PathVariable("idItem") Long id) {
        try {
            servicioInventarioApl.eliminarItem(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/incrementar/{idItem}")
    public ResponseEntity IncrementarItem(@PathVariable("idItem") Long id) {
        try {
            return ResponseEntity.ok(servicioInventarioApl.insertarUno(id));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/decrementar/{idItem}")
    public ResponseEntity DecrementarItem(@PathVariable("idItem") Long id) {
        try {
            return ResponseEntity.ok(servicioInventarioApl.extraerUno(id));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
