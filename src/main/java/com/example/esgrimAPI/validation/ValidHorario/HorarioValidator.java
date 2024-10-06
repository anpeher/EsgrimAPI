package com.example.esgrimAPI.validation.ValidHorario;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class HorarioValidator implements ConstraintValidator<ValidHorario, String> {

    private final String HorarioRegex = "^([01]\\d|2[0-3]):([0-5]\\d)-([01]\\d|2[0-3]):([0-5]\\d)$";
    private boolean nullable;

    /**
     * Se inicializa para poder agregar la opcion de nullable
     */
    @Override
    public void initialize(ValidHorario constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String horario, ConstraintValidatorContext context) {

        if (horario == null && !nullable) {
            return false;
        }

        if (horario.isEmpty()) {
            return false;
        }

        if (!horario.matches(HorarioRegex)) {
            return false;
        }

        String[] horarios = horario.split("-");
        String horaInicio = horarios[0];
        String horaFin = horarios[1];

        try {

            LocalTime horaInicioTime = LocalTime.parse(horaInicio);
            LocalTime horaFinTime = LocalTime.parse(horaFin);
            return horaInicioTime.isBefore(horaFinTime);

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
