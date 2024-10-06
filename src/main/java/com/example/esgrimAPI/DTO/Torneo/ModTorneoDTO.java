package com.example.esgrimAPI.DTO.Torneo;

import com.example.esgrimAPI.validation.DateValidation.ValidDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Constructor JSON para recibir los datos para modificar un torneo
 */
@Getter
@Setter
public class ModTorneoDTO {

    /**
     * Fecha de inicio del torneo.
     */
    @ValidDate(nullable = true)
    private String fechaInicio;

    /**
     * Ubicación del torneo.
     */

    @Size(max = 100, message = "El campo ubicación no puede contener más de 100 caracteres")
    private String ubicacion;
}
