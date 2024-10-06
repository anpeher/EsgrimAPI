package com.example.esgrimAPI.DTO.Usuario;

import com.example.esgrimAPI.Modelo.Usuario.Rol;
import com.example.esgrimAPI.validation.ValidRol.ValidRol;
import lombok.Getter;
import lombok.Setter;

/**
 * Constructor JSON para recibir los datos para cambiar el rol
 */
@Getter
@Setter
public class RolUsuario {

    /**
     * Rol del usuario
     */
    @ValidRol(nullable = false)
    private String rol;
}
