package com.example.esgrimAPI.Modelo.Participante;

import com.example.esgrimAPI.Modelo.Poule.Poule;
import com.example.esgrimAPI.Modelo.Torneo.Torneo;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participante")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 80)
    private String apellidos;

    @Column(name = "ranking", nullable = false)
    private Short ranking;

    @Column(name = "descalificado", nullable = false)
    private Boolean descalificado = false;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties({"nombre", "apellidos", "email", "telefono", "rol", "club", "genero", "clase","edad","clasificacion"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_torneo", nullable = false)
    @JsonIgnoreProperties({"fecha_inicio","ubicacion","estado","modalidad","edad","genero","id_club"})
    private Torneo torneo;

    @ManyToOne
    @JoinColumn(name = "id_poule")
    @JsonIgnoreProperties({"numPoule", "numParticipantes", "eliminatoria", "torneo"})
    private Poule poule;
}
