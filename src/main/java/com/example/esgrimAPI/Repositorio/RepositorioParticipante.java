package com.example.esgrimAPI.Repositorio;


import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;
import com.example.esgrimAPI.Modelo.Torneo.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Participante}
 */
@Repository
public interface RepositorioParticipante extends JpaRepository<Participante, Long> {

    // Buscar los participantes de un torneo y ordenarlos por ranking
    List<Participante> findByTorneoOrderByRankingDesc(Torneo torneo);

    List<Participante> findByTorneoOrderByRankingAsc(Torneo torneo);

    List<Participante> findByTorneoAndDescalificadoFalseOrderByRankingAsc(Torneo torneo);

    List<Participante> findByPoule(Poule poule);
}
