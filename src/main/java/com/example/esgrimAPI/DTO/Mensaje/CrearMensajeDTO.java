package com.example.esgrimAPI.DTO.Mensaje;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Constructor JSON para crear un mensaje
 */
@Getter
@Setter
public class CrearMensajeDTO {

    @NotNull(message = "el contenido es obligatorio")
    @NotBlank(message = "el contenido no puede ir en blanco")
    @Size(max = 100, message = "el campo contenido no puede contener más de 100 palabras")
    private String contenido;

    @NotNull(message = "El id del chat es obligatorio")
    @Min(value = 1, message = "el id mínimo es 1")
    private Long chat;

    @NotNull(message = "El id del usuario es obligatorio")
    @Min(value = 1, message = "el id mínimo es 1")
    private Long usuario;
}
