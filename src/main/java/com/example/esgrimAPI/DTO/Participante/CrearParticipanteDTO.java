package com.example.esgrimAPI.DTO.Participante;

import com.example.esgrimAPI.Modelo.Usuario.Genero;
import com.example.esgrimAPI.validation.ValidGenero.ValidGenero;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;
@Getter
@Setter

/**
 * Constructor JSON para crear un participante para un torneo
 */
public class CrearParticipanteDTO {

    /**
     * Nombre del participante.
     */

    @Size(max = 40, message = "El nombre no puede tener más de 40 caracteres")
    private String nombre;

    /**
     * Apellidos del participante.
     */

    @Size(max = 80, message = "Los apellidos no pueden tener más de 80 caracteres")
    private String apellidos;

    /**
     * Edad del participante.
     */

    @Min(value = 0, message = "La edad mínima es 0")
    private Short edad;

    /**
     * Género del participante.
     */
    @ValidGenero(nullable = true)
    private String genero;

    /**
     * ID del torneo, obligatorio.
     */
    @NotNull(message = "El ID del torneo es obligatorio")
    @Min(value = 1, message = "El ID del torneo mínimo es 1")
    private Long idTorneo;

    @Min(value = 1, message = "El ID del usuario mínimo es 1")
    private Long idUsuario;

}
