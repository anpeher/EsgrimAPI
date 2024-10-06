package com.example.esgrimAPI.Modelo.Torneo;

import com.example.esgrimAPI.Modelo.Club.Club;
import com.example.esgrimAPI.Modelo.Usuario.Genero;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Torneo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "ubicacion", nullable = false, length = 100)
    private String ubicacion;

    @Column(name = "estado", nullable = false, length = 50)
    private Estado estado;

    @Column(name = "modalidad", nullable = false, length = 20)
    private String modalidad;

    @Column(name = "edad", nullable = false)
    private Short edad;

    @Column(name = "genero", nullable = false, length = 6)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "id_club", nullable = false)
    @JsonIgnoreProperties({"nombre","direccion","telefono","email"})
    private Club club;


}
