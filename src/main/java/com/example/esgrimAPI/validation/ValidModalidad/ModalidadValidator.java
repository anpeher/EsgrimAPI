package com.example.esgrimAPI.validation.ValidModalidad;

import com.example.esgrimAPI.Modelo.Clase.Modalidad;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ModalidadValidator implements ConstraintValidator<ValidModalidad, String> {

    private boolean nullable;

    /**
     * Se inicializa para poder agregar la opcion de nullable
     */
    @Override
    public void initialize(ValidModalidad constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
    }
    /**
     * Se comprueba que el string recibido pertenece a uno de los values de {@link Modalidad}
     * @param modalidad string con el valor a comprobar
     * @return verdadero si pertenece a alguno de los 3 posibles valores y negativo si no cumple la condición.
     */
    @Override
    public boolean isValid(String modalidad, ConstraintValidatorContext constraintValidatorContext) {
        if (modalidad == null && !nullable) {
            return false;
        }
        if (modalidad == null){
            return true;
        }

        try {
            Modalidad.valueOf(modalidad);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
