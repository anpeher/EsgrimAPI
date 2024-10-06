package com.example.esgrimAPI.DTO.Chat;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

/**
 * Constructor JSON para recibir los datos para crear un chat
 */
@Getter
@Setter
public class CrearChatDTO {

    /**
     * titulo del chat.
     */
    @NotNull(message = "el titulo es obligatorio")
    @NotBlank(message = "el titulo no puede ir en blanco")
    @Size(max = 100, message = "el campo titulo no puede contener más de 100 palabras")
    private String titulo;

    /**
     * ServicioUsuario que creo el chat
     */
    @NotNull(message = "El id del usuario es obligatorio")
    @Min(value = 1, message = "el id mínimo es 1")
    private long usuarioCreador;

    /**
     * ServicioUsuario que creo el chat
     */
    @NotNull(message = "El id del usuario es obligatorio")
    @Min(value = 1, message = "el id mínimo es 1")
    private long usuarioParticipante;
}
