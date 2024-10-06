package com.example.esgrimAPI.validation.ValidUniqueTelefono;
import com.example.esgrimAPI.validation.ValidModalidad.ModalidadValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para validar que el telefono sea unico.
 * Se comprueba que el campo no sea identico a ninguno otro de la tabla usuarios.
 * Se utiliza en los endpoints de crear y modificar una usuario.
 */
@Constraint(validatedBy = UniqueTelefonoValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTelefono {

    /**
     * Mensaje de error si la validacion falla.
     * @return mensaje informando del error cometido.
     */
    String message() default "El campo telefono debe ser unico y ya existe un usuario con ese telefono";

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
