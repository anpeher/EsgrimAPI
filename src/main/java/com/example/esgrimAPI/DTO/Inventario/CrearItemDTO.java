package com.example.esgrimAPI.DTO.Inventario;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Constructor JSON para crear un item para el inventario
 */
@Getter
@Setter
public class CrearItemDTO {

    /**
     * Nombre del objeto de inventario.
     */
    @NotNull(message = "el nombre del objeto es obligatorio")
    @NotBlank(message = "el nombre del objeto no puede ir en blanco")
    @Size(max = 100, message = "el campo nombre del objeto no puede contener más de 100 palabras")
    private String nombreObjeto;

    @NotNull(message = "el fabricante es obligatorio")
    @NotBlank(message = "el fabricante no puede ir en blanco")
    @Size(max = 100, message = "el campo fabricante no puede contener más de 100 palabras")
    private String fabricante;

    /**
     * Cantidad del objeto disponible en el inventario.
     */
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "el valor mínimo es 0")
    private short cantidad;
}
