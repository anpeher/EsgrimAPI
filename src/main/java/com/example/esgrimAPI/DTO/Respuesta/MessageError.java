package com.example.esgrimAPI.DTO.Respuesta;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Constructor de un json que se envia generarmente con la informacion de un fallo a la hora de ejecutar un endpoint
 */
@Getter
@Setter
public class MessageError {
    /**
     * Mensaje personailizado.
     */
    private List<String> message;

    /**
     * Constructor con un mensaje.
     *
     * @param message El mensaje que se envia en el endpoint.
     */
    public MessageError(List<String> message) {
        this.message = message;
    }

}