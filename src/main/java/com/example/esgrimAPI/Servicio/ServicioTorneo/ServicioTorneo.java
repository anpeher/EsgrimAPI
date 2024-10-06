package com.example.esgrimAPI.Servicio.ServicioTorneo;

import com.example.esgrimAPI.DTO.Directa.RondaDirectaDTO;
import com.example.esgrimAPI.DTO.Torneo.CrearTorneoDTO;
import com.example.esgrimAPI.DTO.Torneo.ModTorneoDTO;
import com.example.esgrimAPI.Modelo.Directa.Directa;
import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;
import com.example.esgrimAPI.Modelo.ResultadoPoule.ResultadoPoule;
import com.example.esgrimAPI.Modelo.Torneo.Torneo;

import java.util.List;

public interface ServicioTorneo {


    Torneo crearTorneo(CrearTorneoDTO crearTorneoDTO);

    Torneo modTorneo(Long id, ModTorneoDTO modTorneoDTO);

    Torneo verTorneo(long id);

    void cerrarTorneo(long id);

    List<Participante> participantesTorneoClasificacionInicial(long id);

    List<Poule> InicializarTorneo(long id);

    List<ResultadoPoule> finalizarPoules(long id);

    List<Participante> clasificacionPostPoules(long id);

    List<Participante> clasificacionParticipantes(long id);

    RondaDirectaDTO rondaDirectas(long id);

    List<Directa> directasTorneo(long id);

    List<Participante> terminarTorneo(long id);

}
