package com.example.esgrimAPI.validation.ValidGenero;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para validar generos.
 * Se comprueba que el genero que se recibe es uno de los 2 posibles.
 * Se utiliza en los endpoints de crear y modificar un usuario.
 */
@Constraint(validatedBy = GeneroValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGenero {

    /**
     * Mensaje de error si la validacion falla.
     * @return mensaje informando del error cometido.
     */
    String message() default "El genero solo puede ser Hombre o Mujer";

    /**
     * Variable para poder comprobar si el valor puede ser nullable.
     * @return false cuando no puede ser nulo y verdadero cuando si puede serlo.
     */
    boolean nullable() default false;

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