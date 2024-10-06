package com.example.esgrimAPI.Modelo.Poule;

import com.example.esgrimAPI.Modelo.Torneo.Torneo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "poule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Poule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_poule", nullable = false)
    private Short numPoule;

    @Column(name = "num_participantes", nullable = false)
    private Short numParticipantes;

    @Column(name = "eliminatoria", nullable = false)
    private Boolean eliminatoria = false;

    @ManyToOne
    @JoinColumn(name = "id_torneo", nullable = false)
    @JsonIgnoreProperties({"fecha_inicio","ubicacion","estado","modalidad","edad","genero","id_club"})
    private Torneo torneo;
}
