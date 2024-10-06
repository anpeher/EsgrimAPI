package com.example.esgrimAPI.validation.DateValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Validador de fechas personalizado para la anotación {@link ValidDate}.
 * En esta clase se realizará lo explicado en el ValidDate. Que no sea nula ni este vacía.
 * El formato de la fecha debe ser "yyyy-mm-dd" y que no sea una fecha futura.
 */
public class DateValidator implements ConstraintValidator<ValidDate, String> {
    private boolean nullable;
    /**
     * Inicializa el validador. No es necesario utilizarlo
     *
     * @param constraintAnnotation la anotación que se usa en este validator
     */
    @Override
    public void initialize(ValidDate constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
    }

    /**
     *Validacion de la fecha.
     * @param fecha es la fecha que nos llega desde el json
     * @param context es el contexto de la validacion
     * @return true si cumple lo mencionado o false si no cumple alguno de los requisitos
     */
    @Override
    public boolean isValid(String fecha, ConstraintValidatorContext context) {
        if (fecha == null && !nullable){
            return false;
        }
        if(fecha == null){
            return true;
        }
        String fechaPattern = "^\\d{4}-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1])$";
        Pattern pattern = Pattern.compile(fechaPattern);
        if (!pattern.matcher(fecha).matches()) {
            return false;
        }
        try {
            LocalDate fechaDate = LocalDate.parse(fecha);
            return !fechaDate.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
