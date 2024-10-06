package com.example.esgrimAPI.Modelo.Clase;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;


/**
 * Entidad que representa las clases que se dan en un club.
 */
@Entity
@Table(name = "clase")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Clase {
    /**
     * Identificador único de la clase.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Modalidad de la clase, solo puede ser Espada, Florete y Sable.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "modalidad", nullable = false, length = 10)
    private Modalidad modalidad;

    /**
     * Array con los dias de la semana que se da clase.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "dias", columnDefinition = "TEXT[]", nullable = false)
    private DiaSemana[] dias;

    /**
     * Horario de inicio de la clase.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horarioInicio;

    /**
     * Horario de fin de la clase.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horarioFin;

    /**
     * Maestro que enseña la clase.
     */
    @OneToOne
    @JoinColumn(name = "maestro")
    @JsonIgnoreProperties({"nombre", "apellidos", "email", "telefono", "rol", "club", "genero", "clase","edad","clasificacion"})
    private Usuario maestro;

}
