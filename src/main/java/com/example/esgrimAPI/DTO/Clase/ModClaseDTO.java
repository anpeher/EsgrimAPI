package com.example.esgrimAPI.DTO.Clase;

import com.example.esgrimAPI.Modelo.Clase.DiaSemana;
import com.example.esgrimAPI.Modelo.Clase.Modalidad;
import com.example.esgrimAPI.validation.DateValidation.ValidDate;
import com.example.esgrimAPI.validation.ValidDias.ValidDias;
import com.example.esgrimAPI.validation.ValidHorario.ValidHorario;
import com.example.esgrimAPI.validation.ValidModalidad.ValidModalidad;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

/**
 * Constructor JSON para recibir los datos para modificar una clase
 */
@Getter
@Setter
public class ModClaseDTO {

    /**
     * Modalidad de la clase.
     */
    @ValidModalidad(nullable = true, message = "La modalidad debe ser ESPADA, SABLE o FLORETE, puede ser nula")
    private String modalidad;

    /**
     * Array con los dias de la semana que se da clase.
     */
    @ValidDias(nullable = true)
    private List<String> dias;

    /**
     * Horario de inicio de la clase.
     */
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "El horario de inicio debe estar en formato HH:mm")
    private String horarioInicio;

    /**
     * Horario de fin de la clase.
     */
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "El horario de fin debe estar en formato HH:mm")
    private String horarioFin;

    @Min(value = 1, message = "el id mínimo es 1")
    private Long maestro;
}
