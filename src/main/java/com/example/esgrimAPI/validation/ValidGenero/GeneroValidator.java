package com.example.esgrimAPI.validation.ValidGenero;

import com.example.esgrimAPI.Modelo.Usuario.Genero;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GeneroValidator implements ConstraintValidator<ValidGenero, String> {

    private boolean nullable;

    /**
     * Se inicializa para poder agregar la opcion de nullable
     */
    @Override
    public void initialize(ValidGenero constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
    }
    /**
     * Se comprueba que el string recibido pertenece a uno de los values de {@link Genero}
     * @param genero string con el valor a comprobar
     * @return verdadero si pertenece a alguno de los 2 posibles valores y negativo si no cumple la condición.
     */
    @Override
    public boolean isValid(String genero, ConstraintValidatorContext constraintValidatorContext) {
        if (genero == null && !nullable) {
            return false;
        } if(genero == null){
            return true;
        }

        try {
            Genero.valueOf(genero);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}