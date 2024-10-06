package com.example.esgrimAPI.DTO.Notificacion;


import com.example.esgrimAPI.Modelo.Usuario.Rol;
import com.example.esgrimAPI.validation.ValidRol.ValidRol;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Constructor JSON para crear una notificacion grupal.
 */
@Getter
@Setter
public class CrearNotificacionGrupalDTO {

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
     * Rol para los que se le desea enviar el aviso.
     */
    //@ValidRol(nullable = true)
    private Rol rol;

    /**
     * Lista de usuarios para enviarles un mensaje.
     */

    @NotEmpty(message = "La lista de usuarios no puede estar vacía")
    @Size(min = 1, message = "Debe haber al menos un id")
    private List<@NotNull(message = "El id de usuario no puede ser nulo") Long> usuarios;

    /**
     * Usuario que creo la notificacion
     */
    @NotNull(message = "El usuario es obligatorio")
    @Min(value = 1, message = "el valor mínimo es 1")
    private long usuario;
}
