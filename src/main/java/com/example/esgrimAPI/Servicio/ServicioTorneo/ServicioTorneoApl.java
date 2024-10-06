package com.example.esgrimAPI.Servicio.ServicioTorneo;

import com.example.esgrimAPI.DTO.Directa.RondaDirectaDTO;
import com.example.esgrimAPI.DTO.Torneo.CrearTorneoDTO;
import com.example.esgrimAPI.DTO.Torneo.ModTorneoDTO;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Club.Club;
import com.example.esgrimAPI.Modelo.Directa.Directa;
import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;
import com.example.esgrimAPI.Modelo.ResultadoPoule.ResultadoPoule;
import com.example.esgrimAPI.Modelo.Torneo.Estado;
import com.example.esgrimAPI.Modelo.Torneo.Torneo;
import com.example.esgrimAPI.Modelo.Usuario.Genero;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Repositorio.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioTorneoApl implements ServicioTorneo{

    private final long idClub = 1;
    private RepositorioTorneo repositorioTorneo;
    private RepositorioClub repositorioClub;
    private RepositorioParticipante repositorioParticipante;
    private RepositorioPoule repositorioPoule;
    private RepositorioResultadoPoule repositorioResultadoPoule;
    private RepositorioDirecta repositorioDirecta;
    private RepositorioUsuario repositorioUsuario;

    @Override
    public Torneo crearTorneo(CrearTorneoDTO crearTorneoDTO) {
        Club club = repositorioClub.findById(idClub)
                .orElseThrow(() -> new IllegalArgumentException("Club no encontrado"));
        return repositorioTorneo.save(obtenerTorneo(crearTorneoDTO, club));
    }

    @Override
    public Torneo modTorneo(Long id, ModTorneoDTO modTorneoDTO) {
        Torneo torneo = repositorioTorneo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        if(modTorneoDTO.getFechaInicio() != null){
            torneo.setFechaInicio(LocalDate.parse(modTorneoDTO.getFechaInicio()));
        }
        Optional.ofNullable(modTorneoDTO.getUbicacion()).ifPresent(torneo::setUbicacion);
        return repositorioTorneo.save(torneo);
    }

    @Override
    public Torneo verTorneo(long id) {
        return repositorioTorneo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
    }

    @Override
    public void cerrarTorneo(long id) {
        Torneo torneo = repositorioTorneo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        torneo.setEstado(Estado.Cerrado);
        repositorioTorneo.save(torneo);
    }

    @Override
    public List<Participante> participantesTorneoClasificacionInicial(long id) {
        Torneo torneo = repositorioTorneo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));

        return repositorioParticipante.findByTorneoOrderByRankingDesc(torneo);
    }

    @Override
    public List<Poule> InicializarTorneo(long id) {

        Torneo torneo = repositorioTorneo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));

        List<Participante> participantes = participantesTorneoClasificacionInicial(id);
        int numParticipantes =  participantes.size();

        if (numParticipantes == 3) {
            return crearPoules(3,1, participantes, torneo);
        }

        if (numParticipantes % 6 == 0) {
            return crearPoules(6, numParticipantes / 6, participantes, torneo);
        } else if (numParticipantes % 7 == 0) {
            return crearPoules(7, numParticipantes / 7, participantes, torneo);
        } else while (numParticipantes > 7){
            crearPoules(7, numParticipantes / 7, participantes, torneo);
            numParticipantes = numParticipantes -7;
        } while (numParticipantes > 6){
            crearPoules(6, numParticipantes / 6, participantes, torneo);
            numParticipantes = numParticipantes -6;
        } while (numParticipantes > 3){
            crearPoules(3, numParticipantes / 3, participantes, torneo);
            numParticipantes = numParticipantes -3;
        }

        return crearPoulesPersonalizadas(participantes, torneo);
    }

    @Override
    public List<ResultadoPoule> finalizarPoules(long id) {
        Torneo torneo = repositorioTorneo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        return repositorioResultadoPoule.findByPouleTorneoOrderByVictoriasDescTocadosDiferenciaDesc(torneo);
    }

    @Override
    public List<Participante> clasificacionPostPoules(long id) {
        List<ResultadoPoule> resultadoPoules = this.finalizarPoules(id);

        for (int i = 0; i < resultadoPoules.size(); i++){
            Participante participante = resultadoPoules.get(i).getParticipante();
            participante.setRanking((short) (i+1));
            repositorioParticipante.save(participante);
        }
        return clasificacionParticipantes(id);
    }

    @Override
    public List<Participante> clasificacionParticipantes(long id) {
        Torneo torneo = repositorioTorneo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        return repositorioParticipante.findByTorneoOrderByRankingAsc(torneo);
    }

    @Override
    public RondaDirectaDTO rondaDirectas(long id) {
        Torneo torneo = repositorioTorneo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        List<Participante> participantes = repositorioParticipante.findByTorneoAndDescalificadoFalseOrderByRankingAsc(torneo);
        RondaDirectaDTO rondaDirectaDTO = this.determinarRonda(participantes, torneo);
        return rondaDirectaDTO;
    }

    @Override
    public List<Directa> directasTorneo(long id) {
        Torneo torneo = repositorioTorneo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        return repositorioDirecta.findByTorneo(torneo);
    }

    @Override
    public List<Participante> terminarTorneo(long id) {
        List<Participante> participantes = clasificacionParticipantes(id);
        if (participantes.size() >= 4){
            participantes.get(3).setRanking((short) 3);
            repositorioParticipante.save(participantes.get(3));
        }
        for(Participante participante : participantes){
            if(participante.getUsuario() != null){
                Usuario usuario = participante.getUsuario();
                usuario.setClasificacion((short) (usuario.getClasificacion() + (1000/participante.getRanking())));
                repositorioUsuario.save(usuario);
            }
        }
        return participantes;
    }

    private List<Poule> crearPoules(int numParticipantes, int numeroPoules, List<Participante> participantes, Torneo torneo){

        List<Poule> poules = new ArrayList<>();
        short numeroPoul = 0;
        int numeroParticipanteInicial = 0;
        for (int i = 0; i < numeroPoules; i++){
            Poule poule = new Poule();
            poule.setNumPoule(numeroPoul);
            poule.setTorneo(torneo);
            poules.add(poule);
            poule.setNumParticipantes((short) numParticipantes);
            numeroPoul++;
            repositorioPoule.save(poule);
            for (int x = numeroParticipanteInicial; x < participantes.size(); x+=numeroPoules){
                Participante participante = participantes.get(x);
                participante.setPoule(poule);
                repositorioParticipante.save(participante);
            }
            numeroParticipanteInicial++;
        }

        return poules;
    }

    List<Poule> crearPoulesPersonalizadas(List<Participante> participantes, Torneo torneo) {

        List<Poule> poules = new ArrayList<>();
        return poules;
    }


    Torneo obtenerTorneo(CrearTorneoDTO crearTorneoDTO, Club club){

        Torneo torneo = new Torneo();
        torneo.setEdad(crearTorneoDTO.getEdad());
        torneo.setEstado(Estado.Abierto);
        torneo.setClub(club);
        torneo.setModalidad(crearTorneoDTO.getModalidad());
        torneo.setFechaInicio(LocalDate.parse(crearTorneoDTO.getFechaInicio()));
        torneo.setGenero(Genero.valueOf(crearTorneoDTO.getGenero()));
        torneo.setUbicacion(crearTorneoDTO.getUbicacion());
        torneo.setNombre(crearTorneoDTO.getNombre());
        return torneo;
    }

    private List<Directa> crearCombatesDirecta(List<Participante> participantes, Torneo torneo, int numCombates){
        List<Directa> directas = new ArrayList<>();
        int participantesExcluidos =  numCombates - participantes.size();
        while (participantesExcluidos > 0){
            participantes.removeFirst();
            participantesExcluidos --;
        }
        int i = 0;
        int x = participantes.size() -1;
        while (x > i){
            Directa directa = new Directa(participantes.get(i), participantes.get(x), torneo);
            directas.add(directa);
            i++;
            x--;
        }
        repositorioDirecta.saveAll(directas);
        return directas;
    }

    private RondaDirectaDTO determinarRonda(List<Participante> participantes, Torneo torneo  ) {
        RondaDirectaDTO rondaDirectaDTO = new RondaDirectaDTO();
        int numParticipantes = participantes.size();
        if (numParticipantes == 1) {
            rondaDirectaDTO.setRonda("Finalizado");
            rondaDirectaDTO.setDirectas(new ArrayList<>());
            return rondaDirectaDTO;
        } else if (numParticipantes == 2) {
            rondaDirectaDTO.setRonda("Final");
            rondaDirectaDTO.setDirectas(crearCombatesDirecta(participantes, torneo,2));
            return rondaDirectaDTO;
        } else if (numParticipantes <= 4) {
            rondaDirectaDTO.setRonda("Semifinales");
            rondaDirectaDTO.setDirectas(crearCombatesDirecta(participantes, torneo,4));
            return rondaDirectaDTO;
        } else if (numParticipantes <= 8) {
            rondaDirectaDTO.setRonda("Cuartos de final");
            rondaDirectaDTO.setDirectas(crearCombatesDirecta(participantes, torneo,8));
            return rondaDirectaDTO;
        } else if (numParticipantes <= 16) {
            rondaDirectaDTO.setRonda("Octavos de final");
            rondaDirectaDTO.setDirectas(crearCombatesDirecta(participantes, torneo,16));
            return rondaDirectaDTO;
        } else if (numParticipantes <= 32) {
            rondaDirectaDTO.setRonda("Dieciseisavos de final");
            rondaDirectaDTO.setDirectas(crearCombatesDirecta(participantes, torneo,32));
            return rondaDirectaDTO;
        } else  {
            rondaDirectaDTO.setRonda("Treintaidosavos de final");
            rondaDirectaDTO.setDirectas(crearCombatesDirecta(participantes, torneo,64));
            return rondaDirectaDTO;

        }
    }
}
