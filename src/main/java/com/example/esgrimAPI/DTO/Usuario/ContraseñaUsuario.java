package com.example.esgrimAPI.DTO.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Constructor JSON para recibir los datos para cambiar una contrasena.
 */
@Getter
@Setter
public class ContraseñaUsuario {

    /**
     * Contraseña del usuario.
     */

    @NotNull(message = "La contraseña no puede ser nulo")
    @NotBlank(message = "La contraseña no puede ir en blanco")
    @Size(max = 100, message = "el campo contraseña no puede contener mas de 100 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{10,}$", message = "formato contraseña incorrecto. Debe tener mínimo 10 carácteres, con una mayúscula, un dígito y un símbolo.")
    private String contraseña;
}
