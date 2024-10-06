package com.example.esgrimAPI.validation.ValidDias;

import com.example.esgrimAPI.Modelo.Clase.DiaSemana;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class DiasValidator implements ConstraintValidator<ValidDias, List<String>> {
    private boolean nullable;

    /**
     * Se inicializa para poder agregar la opcion de nullable
     */

    @Override
    public void initialize(ValidDias constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
    }

    /**
     * Se comprueba que el array recibido no esta vacio y cada elemento pertenece al enum de {@link DiaSemana}
     * @param diasSemana el array con los valores.
     * @return verdadero si cumple los requisitos y negativo si no cumple la condición.
     */
    @Override
    public boolean isValid(List<String> diasSemana, ConstraintValidatorContext constraintValidatorContext) {
        if (diasSemana == null && !nullable) {
            return false;
        } else if (diasSemana == null) {
            return true;
        }
        if (diasSemana.isEmpty()){
            return false;
        }
        try{
            for (String dia : diasSemana){
                DiaSemana.valueOf(dia);
            }
            return true;
        }catch (IllegalArgumentException e) {
            return false;
        }
    }


}
