package com.example.esgrimAPI.DTO.Torneo;

import com.example.esgrimAPI.validation.DateValidation.ValidDate;
import com.example.esgrimAPI.validation.ValidGenero.ValidGenero;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * Constructor JSON para recibir los datos para crear un torneo
 */
@Getter
@Setter
public class CrearTorneoDTO {
    /**
     * Nombre del torneo.
     */
    @NotNull(message = "El nombre es obligatorio")
    @NotBlank(message = "El nombre no puede ir en blanco")
    @Size(max = 80, message = "El campo nombre no puede contener más de 80 caracteres")
    private String nombre;

    /**
     * Fecha de inicio del torneo.
     */
    @ValidDate()
    private String fechaInicio;

    /**
     * Ubicación del torneo.
     */
    @NotNull(message = "La ubicación es obligatoria")
    @NotBlank(message = "La ubicación no puede ir en blanco")
    @Size(max = 100, message = "El campo ubicación no puede contener más de 100 caracteres")
    private String ubicacion;

    /**
     * Modalidad del torneo.
     */
    @NotNull(message = "La modalidad es obligatoria")
    @NotBlank(message = "La modalidad no puede ir en blanco")
    @Size(max = 20, message = "El campo modalidad no puede contener más de 20 caracteres")
    private String modalidad;

    /**
     * Edad mínima para participar en el torneo.
     */
    @NotNull(message = "La edad mínima es obligatoria")
    @Min(value = 0, message = "La edad mínima no puede ser negativa")
    private Short edad;

    /**
     * Género del torneo.
     */
    @NotNull(message = "El género es obligatorio")
    @ValidGenero
    private String genero;

}
