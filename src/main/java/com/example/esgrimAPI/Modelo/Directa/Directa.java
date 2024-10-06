package com.example.esgrimAPI.Modelo.Directa;

import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;
import com.example.esgrimAPI.Modelo.Torneo.Torneo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "directa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Directa {

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
    @JoinColumn(name = "id_torneo", nullable = false)
    @JsonIgnoreProperties({"fecha_inicio","ubicacion","estado","modalidad","edad","genero","id_club"})
    private Torneo torneo;

    public Directa(Participante participante1, Participante participante2, Torneo torneo){
        this.tirador1 = participante1;
        this.tirador2 = participante2;
        this.resultadoTirador1 = 0;
        this.resultadoTirador2 = 0;
        this.torneo = torneo;
    }
}
