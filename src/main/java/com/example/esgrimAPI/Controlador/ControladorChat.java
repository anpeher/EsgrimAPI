package com.example.esgrimAPI.Controlador;

import com.example.esgrimAPI.DTO.Chat.CrearChatDTO;
import com.example.esgrimAPI.DTO.Chat.ModChatDTO;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Chat.Chat;
import com.example.esgrimAPI.Modelo.Mensaje.Mensaje;
import com.example.esgrimAPI.Servicio.ServicioChat.ServicioChatApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ControladorChat {

    private final ServicioChatApl servicioChatApl;

    @PostMapping
    public ResponseEntity generarChat(@Valid @RequestBody CrearChatDTO crearChatDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //se validan los datos del json y en caso de fallo salta con un bad request
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return new ResponseEntity<>(servicioChatApl.crearChat(crearChatDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idChat}")
    public ResponseEntity modificarChat(@PathVariable("idChat") Long id, @Valid @RequestBody ModChatDTO modChatDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            return ResponseEntity.ok(servicioChatApl.modChat(id, modChatDTO));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idChat}")
    public ResponseEntity obtenerChat(@PathVariable("idChat") Long id) {
        try {
            return ResponseEntity.ok(servicioChatApl.verChat(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idChat}")
    public ResponseEntity eliminarChat(@PathVariable("idChat") Long id) {
        try {
            servicioChatApl.eliminarChat(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/chats/{idUsuario}")
    public ResponseEntity obtenerChatsUsuarios(@PathVariable("idUsuario") Long id) {
        try {
            List<Chat> chat = servicioChatApl.chatsUsuario(id);
            if (chat.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(chat);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mensajes/{idChat}")
    public ResponseEntity obtenerMensajesChat(@PathVariable("idChat") Long id) {
        try {
            List<Mensaje> mensaje = servicioChatApl.MensajesChats(id);
            if (mensaje.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(mensaje);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
