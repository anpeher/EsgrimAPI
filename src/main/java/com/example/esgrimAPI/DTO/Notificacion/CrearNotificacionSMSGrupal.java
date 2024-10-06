package com.example.esgrimAPI.DTO.Notificacion;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO para recibir json para crar una notificacion sms y enviarla a varias personas
 */
@Getter
@Setter
public class CrearNotificacionSMSGrupal {

    /**
     * Contenido de la notificación.
     */
    @NotNull(message = "el contenido es obligatorio")
    @NotBlank(message = "el contenido no puede ir en blanco")
    @Size(max = 100, message = "el campo contenido no puede contener más de 100 palabras")
    private String contenido;

    /**
     * Usuario que creo la notificacion
     */
    @NotNull(message = "El usuario es obligatorio")
    @Min(value = 1, message = "el valor mínimo es 1")
    private long usuario;

    /**
     * Lista de usuarios para enviarles un mensaje.
     */
    @NotEmpty(message = "La lista de usuarios no puede estar vacía")
    @Size(min = 1, message = "Debe haber al menos un id")
    private List<@NotNull(message = "El id de usuario no puede ser nulo") Long> usuarios;

}
