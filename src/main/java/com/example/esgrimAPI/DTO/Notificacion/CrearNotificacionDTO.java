package com.example.esgrimAPI.DTO.Notificacion;

import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 * Constructor JSON para crear una notificacion.
 */
@Getter
@Setter
public class CrearNotificacionDTO {

    /**
     * Contenido de la notificación.
     */
    @NotNull(message = "el contenido es obligatorio")
    @NotBlank(message = "el contenido no puede ir en blanco")
    @Size(max = 100, message = "el campo contenido no puede contener más de 100 palabras")
    private String contenido;

    /**
     * Título de la notificación.
     */
    @NotNull(message = "el titulo es obligatorio")
    @NotBlank(message = "el titulo no puede ir en blanco")
    @Size(max = 100, message = "el campo titulo no puede contener más de 100 palabras")
    private String titulo;
    /**
     * Usuario que creo la notificacion
     */
    @NotNull(message = "El usuario es obligatorio")
    @Min(value = 1, message = "el valor mínimo es 1")
    private long usuario;
}
