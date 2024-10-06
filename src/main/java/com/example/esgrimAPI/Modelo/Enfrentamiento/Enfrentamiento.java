package com.example.esgrimAPI.Modelo.Enfrentamiento;

import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enfrentamiento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Enfrentamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "resultado_tirador1")
    private Short resultadoTirador1 = 0;

    @NotNull
    @Column(name = "resultado_tirador2")
    private Short resultadoTirador2 = 0;


    @ManyToOne
    @JoinColumn(name = "id_tirador1", nullable = false)
    @JsonIgnoreProperties({"edad", "genero", "ranking", "descalificado", "torneo", "poule"})
    private Participante tirador1;


    @ManyToOne
    @JoinColumn(name = "id_tirador2", nullable = false)
    @JsonIgnoreProperties({"edad", "genero", "ranking", "descalificado", "torneo", "poule"})
    private Participante tirador2;


    @ManyToOne
    @JoinColumn(name = "id_poule", nullable = false)
    @JsonIgnoreProperties({"numPoule", "numParticipantes", "eliminatoria", "torneo"})
    private Poule poule;

    public Enfrentamiento(Participante participante1, Participante participante2, Poule poule){
        this.tirador1 = participante1;
        this.tirador2 = participante2;
        this.resultadoTirador1 = 0;
        this.resultadoTirador2 = 0;
        this.poule = poule;
    }
}
