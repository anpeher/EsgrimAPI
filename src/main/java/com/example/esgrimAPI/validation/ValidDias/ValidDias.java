package com.example.esgrimAPI.validation.ValidDias;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para validar el array de los dias.
 * Se comprueba que que el array no este vacío y que cada elemento corresponda a un día del enum.
 * Se utiliza en los endpoints de crear y modificar una clase.
 */
@Constraint(validatedBy = DiasValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDias {

    /**
     * Mensaje de error si la validacion falla.
     * @return mensaje informando del error cometido.
     */
    String message() default "Los dias deben de devolverse en un array de dias, el cual debe contener dias de la semana. No puede ser nulo ni mandar un array vacío";

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
