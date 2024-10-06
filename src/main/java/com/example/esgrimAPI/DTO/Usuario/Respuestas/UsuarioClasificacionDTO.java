package com.example.esgrimAPI.DTO.Usuario.Respuestas;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

/**
 * Constructor JSON para enviar los datos para informar sobre una lista de usuarios ordenados por clasificacion
 */
@Getter
@Setter
public class UsuarioClasificacionDTO {

    /**
     * posicion en la que ha quedado.
     */
    private short posicion;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Apellido del usuario.
     */
    private String apellidos;

    /**
     * Clasificación del usuario.
     */
    @Column(name = "clasificacion", nullable = false)
    private Short clasificacion = 0;
}
