package com.example.esgrimAPI.DTO.Inventario;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Constructor JSON para modificar un item
 */
@Getter
@Setter
public class ModItemDTO {
    /**
     * Nombre del objeto de inventario.
     */
    @Size(min = 1, message = "El campo nombre no puede estar vacío si se proporciona")
    @Size(max = 100, message = "el campo nombre del objeto no puede contener más de 100 palabras")
    private String nombreObjeto;

    @Size(min = 1, message = "El campo fabricante no puede estar vacío si se proporciona")
    @Size(max = 100, message = "el campo fabricante no puede contener más de 100 palabras")
    private String fabricante;

    /**
     * Cantidad del objeto disponible en el inventario.
     */

    @Min(value = 0, message = "el valor mínimo es 0")
    private Short cantidad;
}
