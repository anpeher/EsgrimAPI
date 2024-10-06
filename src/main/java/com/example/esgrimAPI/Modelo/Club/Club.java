package com.example.esgrimAPI.Modelo.Club;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Entidad que representa el club al que pertenece la aplicacion.
 */
@Entity
@Table(name = "club")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Club {

    /**
     * Identificador único del club.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * nombre del club.
     */
    @Column(name = "nombre ", nullable = false)
    private String nombre;

    /**
     * direccion del club.
     */
    @Column(name = "direccion", nullable = false)
    private String direccion;

    /**
     * telefono del club.
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * mail del club.
     */
    @Column(name = "email")
    private String email;

}
