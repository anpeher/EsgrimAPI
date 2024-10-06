package com.example.esgrimAPI.validation.ValidHorario;
import com.example.esgrimAPI.validation.ValidModalidad.ModalidadValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para validar horarios.
 * Se comprueba que las fechas esten bien escritas y que la primera sea menor que la segunda.
 * Se utiliza en los endpoints de crear y modificar una clase.
 */
@Constraint(validatedBy = HorarioValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHorario {

    /**
     * Mensaje de error si la validacion falla.
     * @return mensaje informando del error cometido.
     */
    String message() default "El formato del horario debe de ser el siguiente HH:mm-HH:mm";

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
