package com.example.esgrimAPI.DTO.Notificacion;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Constructor JSON para crear una notificacion via SMS.
 */
@Getter
@Setter
public class CrearNotificacionSMS {

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
     * Usuario que recibe  el mensaje
     */
    @NotNull(message = "El usuario es obligatorio")
    @Min(value = 1, message = "el valor mínimo es 1")
    private long usuarioReceptor;
}
