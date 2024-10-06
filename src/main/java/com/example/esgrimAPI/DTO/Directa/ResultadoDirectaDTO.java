package com.example.esgrimAPI.DTO.Directa;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;


/**
 * Constructor JSON para recibir los datos para insertar una directa
 */
@Getter
@Setter
public class ResultadoDirectaDTO {

    @NotNull(message = "El resultado no puede ser nulo")
    @Min(value = 0, message = "El valor mínimo permitido es 0")
    @Max(value = 15, message = "El valor máximo permitido es 15")
    private int resultado1;

    @NotNull(message = "El resultado no puede ser nulo")
    @Min(value = 0, message = "El valor mínimo permitido es 0")
    @Max(value = 15, message = "El valor máximo permitido es 15")
    private int resultado2;
}
