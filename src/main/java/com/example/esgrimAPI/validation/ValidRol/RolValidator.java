package com.example.esgrimAPI.validation.ValidRol;

import com.example.esgrimAPI.Modelo.Usuario.Rol;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RolValidator implements ConstraintValidator<ValidRol, String> {

    private boolean nullable;

    /**
     * Se inicializa para poder agregar la opcion de nullable
     */
    @Override
    public void initialize(ValidRol constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
    }
    /**
     * Se comprueba que el string recibido pertenece a uno de los values de {@link Rol}
     * @param rol string con el valor a comprobar
     * @return verdadero si pertenece a alguno de los 2 posibles valores y negativo si no cumple la condición.
     */
    @Override
    public boolean isValid(String rol, ConstraintValidatorContext constraintValidatorContext) {
        if (rol == null && !nullable){
            return false;
        }
        if (rol == null) {
            return true;
        }
        try {
            Rol.valueOf(rol);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}