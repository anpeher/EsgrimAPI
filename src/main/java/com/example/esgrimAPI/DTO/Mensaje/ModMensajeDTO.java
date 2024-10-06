package com.example.esgrimAPI.DTO.Mensaje;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Constructor JSON para modificar un mensaje
 */
@Getter
@Setter
public class ModMensajeDTO {

    @NotNull(message = "el contenido es obligatorio")
    @NotBlank(message = "el contenido no puede ir en blanco")
    @Size(max = 100, message = "el campo contenido no puede contener más de 100 palabras")
    private String contenido;

}
