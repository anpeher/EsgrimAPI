package com.example.esgrimAPI.validation.ValidUniqueTelefono;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.esgrimAPI.Repositorio.RepositorioUsuario;

/**
 * Se comprueba que el telefono sea unico.
 */
public class UniqueTelefonoValidator implements ConstraintValidator<UniqueTelefono, String> {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    /**
     * Si el telefono esta en el repositorio de usuario entonces es que existe y por tanto es no valido y devuelve false, en caso contrario devuelve true.
     * @param telefono el string que se debe de comprobar.
     * @return true si se ha complido tod0 y en caso contrario devuelve false.
     */
    @Override
    public boolean isValid(String telefono, ConstraintValidatorContext context) {
        if (telefono == null || telefono.isBlank()) {
            return true; // Si es nulo o en blanco, lo manejan otras validaciones (@NotNull, @NotBlank)
        }
        return !repositorioUsuario.existsByTelefono(telefono);
    }
}