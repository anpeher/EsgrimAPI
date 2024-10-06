package com.example.esgrimAPI.Repositorio;

import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;
import com.example.esgrimAPI.Modelo.ResultadoPoule.ResultadoPoule;
import com.example.esgrimAPI.Modelo.Torneo.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link ResultadoPoule}
 */
@Repository
public interface RepositorioResultadoPoule extends JpaRepository<ResultadoPoule, Long> {

    List<ResultadoPoule> findByPouleTorneoOrderByVictoriasDescTocadosDiferenciaDesc(Torneo torneo);

    List<ResultadoPoule> findByPoule(Poule poule);

    ResultadoPoule findByParticipante(Participante participante);
}
