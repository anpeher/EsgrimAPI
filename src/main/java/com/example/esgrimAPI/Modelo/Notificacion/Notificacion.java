package com.example.esgrimAPI.Modelo.Notificacion;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

/**
 * Entidad que representa lan notificaciones que se envian.
 */
@Entity
@Table(name = "Notificacion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notificacion {

    /**
     * Identificador único de la notificación.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Contenido de la notificación.
     */
    @Column(name = "contenido", nullable = false, length = 100, columnDefinition = "TEXT")
    private String contenido;

    /**
     * Título de la notificación.
     */
    @Column(name = "titulo", nullable = false, length = 100, columnDefinition = "TEXT")
    private String titulo;

    /**
     * Fecha de la notificación.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private LocalDate fecha = LocalDate.now();

    /**
     * ServicioUsuario asociado a la notificación.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"nombre", "apellidos", "email", "telefono", "rol", "club", "genero", "clase","edad","clasificacion"})
    private Usuario usuario;
}
