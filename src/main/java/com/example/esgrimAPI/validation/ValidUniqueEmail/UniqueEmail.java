package com.example.esgrimAPI.validation.ValidUniqueEmail;

import com.example.esgrimAPI.validation.ValidUniqueEmail.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para validar que el email sea unico.
 * Se comprueba que el campo no sea identico a ninguno otro de la tabla usuarios.
 * Se utiliza en los endpoints de crear y modificar una usuario.
 */
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    /**
     * Mensaje de error si la validacion falla.
     * @return mensaje informando del error cometido.
     */
    String message() default "El campo email debe ser unico y ya existe un usuario con ese email";

    /**
     * Grupos de validación a los que pertenece esta anotación.
     *
     * @return los grupos de validación.
     */
    Class<?>[] groups() default {};

    /**
     * Carga útil adicional.
     *
     * @return Carga útil adicional.
     */
    Class<? extends Payload>[] payload() default {};
}
