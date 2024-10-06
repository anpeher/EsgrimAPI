package com.example.esgrimAPI.Modelo.ResultadoPoule;


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
@Table(name = "Resultado_Poule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultadoPoule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "victorias")
    private Short victorias;

    @NotNull
    @Column(name = "tocados_diferencia")
    private Short tocadosDiferencia;

    @ManyToOne
    @JoinColumn(name = "id_participante", nullable = false)
    @JsonIgnoreProperties({"edad", "genero", "ranking", "descalificado", "torneo", "poule"})
    private Participante participante;


    @ManyToOne
    @JoinColumn(name = "id_poule", nullable = false)
    @JsonIgnoreProperties({"numPoule", "numParticipantes", "eliminatoria", "torneo"})
    private Poule poule;
}
