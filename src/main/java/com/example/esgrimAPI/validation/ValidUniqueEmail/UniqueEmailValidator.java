package com.example.esgrimAPI.validation.ValidUniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.esgrimAPI.Repositorio.RepositorioUsuario;

/**
 * Se comprueba que el email sea unico.
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    /**
     * Si el email esta en el repositorio de usuario entonces es que existe y por tanto es no valido y devuelve false, en caso contrario devuelve true.
     * @param email el string que se debe de comprobar.
     * @return true si se ha complido tod0 y en caso contrario devuelve false.
     */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return true; // Si es nulo o en blanco, lo manejan otras validaciones (@NotNull, @NotBlank)
        }
        return !repositorioUsuario.existsByEmail(email);
    }
}
