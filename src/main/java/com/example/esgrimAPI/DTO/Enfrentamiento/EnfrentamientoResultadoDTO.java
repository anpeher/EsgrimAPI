package com.example.esgrimAPI.DTO.Enfrentamiento;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;


/**
 * Constructor JSON para recibir los datos para insertar un enfrentamiento
 */
@Getter
@Setter
public class EnfrentamientoResultadoDTO {

    @NotNull(message = "El resultado no puede ser nulo")
    @Min(value = 0, message = "El valor mínimo permitido es 0")
    @Max(value = 5, message = "El valor máximo permitido es 5")
    private int resultado1;

    @NotNull(message = "El resultado no puede ser nulo")
    @Min(value = 0, message = "El valor mínimo permitido es 0")
    @Max(value = 5, message = "El valor máximo permitido es 5")
    private int resultado2;
}
