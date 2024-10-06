package com.example.esgrimAPI.DTO.Club;


import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;


/**
 * Constructor JSON para recibir los datos para modificar el club
 */
@Getter
@Setter
public class ClubDto {

    /**
     * nombre del club.
     */
    @Size(max = 40, message = "el campo nombre no puede contener mas de 40 caracteres")
    private String nombre;

    /**
     * direccion del club.
     */

    @Size(max = 15, message = "el campo direccion no puede contener mas de 100 caracteres")
    private String direccion;

    /**
     * telefono del club.
     */
    @Size(max = 15, message = "el campo telefono no puede contener mas de 15 caracteres")
    @Pattern(regexp = "\\d{9,15}", message = "formato del telefono incorrecto")
    private String telefono;

    /**
     * mail del club.
     */
    @Size(max = 100, message = "el campo email no puede contener mas de 100 caracteres")
    @Pattern(regexp = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$", message = "formato mail incorrecto")
    private String email;
}
