package com.example.esgrimAPI.DTO.Usuario;

import com.example.esgrimAPI.Modelo.Usuario.Genero;
import com.example.esgrimAPI.Modelo.Usuario.Rol;
import com.example.esgrimAPI.validation.DateValidation.ValidDate;
import com.example.esgrimAPI.validation.ValidGenero.ValidGenero;
import com.example.esgrimAPI.validation.ValidRol.ValidRol;
import com.example.esgrimAPI.validation.ValidUniqueEmail.UniqueEmail;
import com.example.esgrimAPI.validation.ValidUniqueTelefono.UniqueTelefono;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Constructor JSON para recibir los datos para crear un usuario
 */
@Getter
@Setter
public class CrearUsuarioDTO {

    /**
     * Nombre del usuario.
     */
    @NotNull(message = "el nombre es obligatorio")
    @NotBlank(message = "el nombre no puede ir en blanco")
    @Size(max = 40, message = "el campo nombre no puede contener más de 40 palabras")
    private String nombre;

    /**
     * Apellido del usuario.
     */
    @NotNull(message = "los apellidos es obligatorio")
    @NotBlank(message = "los apellidos no puede ir en blanco")
    @Size(max = 80, message = "el campo apellidos no puede contener más de 80 palabras")
    private String apellidos;

    /**
     * Fecha de nacimiento del usuario.
     */
    @ValidDate()
    private String fechaNacimiento;

    /**
     * Correo electrónico del usuario.
     */
    @NotNull(message = "El email es obligatorio")
    @NotBlank(message = "El email no puede ir en blanco")
    @Size(max = 100, message = "el campo email no puede contener mas de 100 caracteres")
    @Pattern(regexp = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$", message = "formato mail incorrecto")
    @UniqueEmail
    private String email;

    /**
     * Contraseña del usuario.
     */
    @NotNull(message = "La contraseña es obligatoria")
    @NotBlank(message = "La contraseña no puede ir en blanco")
    @Size(max = 100, message = "el campo contraseña no puede contener mas de 100 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{10,}$", message = "formato contraseña incorrecto. Debe tener mínimo 10 carácteres, con una mayúscula, un dígito y un símbolo.")
    private String contraseña;

    /**
     * Genero del usuario.
     */
    @ValidGenero
    private String genero;

    /**
     * Telefono del usuario.
     */
    @NotNull(message = "El telefono es obligatorio")
    @NotBlank(message = "El telefono no puede ir en blanco")
    @Pattern(regexp = "\\d{9,15}", message = "formato del telefono incorrecto")
    @UniqueTelefono()
    private String telefono;

    /**
     * Rol del usuario
     */
    @ValidRol
    private String rol;

}
