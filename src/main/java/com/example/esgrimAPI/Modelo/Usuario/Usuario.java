package com.example.esgrimAPI.Modelo.Usuario;
import com.example.esgrimAPI.Modelo.Clase.Clase;
import com.example.esgrimAPI.Modelo.Club.Club;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

/**
 * Entidad que representa a los usuarios que pertenecen al club.
 */
@Entity
@Table(name = "Usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del usuario.
     */
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;

    /**
     * Apellidos del usuario.
     */
    @Column(name = "apellidos", nullable = false, length = 80)
    private String apellidos;

    /**
     * Fecha de nacimiento del usuario.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate fechaNacimiento;

    /**
     * Edad del usuario.
     */
    @Transient
    private short edad;

    /**
     * Correo electrónico del usuario.
     */
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    /**
     * Contraseña del usuario.
     */
    @Column(name = "contraseña", nullable = false, length = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contraseña;

    /**
     * Genero del usuario.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false, length = 6)
    private Genero genero;

    /**
     * Telefono del usuario.
     */
    @Column(name = "telefono", nullable = false, unique = true, length = 15)
    private String telefono;

    /**
     * Rol del usuario
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 20)
    private Rol rol;

    /**
     * Si el usuario está activo
     */
    @Column(name = "activo", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean activo = true;

    /**
     * Clasificación del usuario.
     */
    @Column(name = "clasificacion", nullable = false)
    private Short clasificacion = 0;

    /**
     * Clase a la que pertenece el usuario.
     */
    @ManyToOne
    @JoinColumn(name = "id_clase")
    @JsonIgnoreProperties({"modalidad","dias","horarioInicio","horarioFin","maestro"})
    private Clase clase = null;

    /**
     * Club al que pertenece el usuario.
     */
    @ManyToOne
    @JoinColumn(name = "id_club", nullable = false)
    @JsonIgnoreProperties({"nombre","direccion","telefono","email"})
    private Club club;

    /**
     * Calcula la edad de la persona a partir de la fecha de nacimiento.
     * @return la edad del usuario en años.
     */
    public int getEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }
}
