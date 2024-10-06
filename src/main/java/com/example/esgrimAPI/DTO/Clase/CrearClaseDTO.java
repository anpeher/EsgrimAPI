package com.example.esgrimAPI.DTO.Clase;

import com.example.esgrimAPI.Modelo.Clase.DiaSemana;
import com.example.esgrimAPI.Modelo.Clase.Modalidad;
import com.example.esgrimAPI.validation.DateValidation.ValidDate;
import com.example.esgrimAPI.validation.ValidDias.ValidDias;
import com.example.esgrimAPI.validation.ValidHorario.ValidHorario;
import com.example.esgrimAPI.validation.ValidModalidad.ValidModalidad;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

import java.time.LocalTime;
import java.util.List;

/**
 * Constructor JSON para recibir los datos para crear una clase
 */
@Getter
@Setter
public class CrearClaseDTO {

    /**
     * Modalidad de la clase.
     */
    @ValidModalidad(nullable = false, message = "La modalidad debe ser ESPADA, SABLE o FLORETE y no puede ser nula")
    private String modalidad;

    /**
     * Array con los dias de la semana que se da clase.
     */
    @ValidDias()
    private List<String> dias;

    /**
     * Horario de inicio de la clase.
     */
    @NotNull(message = "El horario de inicio es obligatorio")
    @NotBlank(message = "El horario de inicio no puede estar en blanco")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "El horario de inicio debe estar en formato HH:mm")
    private String horarioInicio;

    /**
     * Horario de fin de la clase.
     */
    @NotNull(message = "El horario de fin es obligatorio")
    @NotBlank(message = "El horario de fin no puede estar en blanco")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "El horario de fin debe estar en formato HH:mm")
    private String horarioFin;

    @NotNull(message = "el maestro no puede ser nulo")
    @Min(value = 1, message = "el id mínimo es 1")
    private Long maestro;
}
