package com.example.esgrimAPI.DTO.Usuario;

import com.example.esgrimAPI.validation.ValidGenero.ValidGenero;
import com.example.esgrimAPI.validation.ValidUniqueEmail.UniqueEmail;
import com.example.esgrimAPI.validation.ValidUniqueTelefono.UniqueTelefono;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 * Constructor JSON para recibir los datos para crear un usuario
 */
@Getter
@Setter
public class ModUsuarioDTO {

    /**
     * Nombre del usuario.
     */
    @Size(max = 40, message = "el campo nombre no puede contener más de 40 palabras")
    private String nombre;

    /**
     * Apellido del usuario.
     */
    @Size(max = 80, message = "el campo apellidos no puede contener más de 80 palabras")
    private String apellidos;

    /**
     * Correo electrónico del usuario.
     */

    @Size(max = 100, message = "el campo email no puede contener mas de 100 caracteres")
    @Pattern(regexp = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$", message = "formato mail incorrecto")
    @UniqueEmail
    private String email;

    /**
     * Genero del usuario.
     */
    @ValidGenero(nullable = true)
    private String genero;

    /**
     * Telefono del usuario.
     */

    @Pattern(regexp = "\\d{9,15}", message = "formato del telefono incorrecto")
    @UniqueTelefono
    private String telefono;

}
