package com.example.esgrimAPI.DTO.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotNull(message = "El mail no puede ser nulo")
    @NotBlank(message = "El mail no puede ir en blanco")
    private String email;

    /**
     * Contraseña del usuario.
     */

    @NotNull(message = "La contraseña no puede ser nulo")
    @NotBlank(message = "La contraseña no puede ir en blanco")
   private String password;


}
