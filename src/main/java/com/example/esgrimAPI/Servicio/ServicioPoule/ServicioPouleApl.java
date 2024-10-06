package com.example.esgrimAPI.Servicio.ServicioPoule;

import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Enfrentamiento.Enfrentamiento;
import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;
import com.example.esgrimAPI.Modelo.ResultadoPoule.ResultadoPoule;
import com.example.esgrimAPI.Repositorio.RepositorioEnfrentamiento;
import com.example.esgrimAPI.Repositorio.RepositorioParticipante;
import com.example.esgrimAPI.Repositorio.RepositorioPoule;
import com.example.esgrimAPI.Repositorio.RepositorioResultadoPoule;
import com.example.esgrimAPI.Servicio.ServicioPoule.TorneoFactory.TablonFactory;
import com.example.esgrimAPI.Servicio.ServicioPoule.TorneoFactory.TablonPoule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServicioPouleApl implements ServicioPoule{

    private RepositorioParticipante repositorioParticipante;
    private RepositorioPoule repositorioPoule;
    private RepositorioEnfrentamiento repositorioEnfrentamiento;
    private RepositorioResultadoPoule repositorioResultadoPoule;
    private TablonFactory tablonFactory;

    @Override
    public List<Enfrentamiento> CrearEnfrentamientos(long id) {
        Poule poule = repositorioPoule.findById(id).orElseThrow(() -> new ResourceNotFoundException("Poule no encontrada"));
        List<Participante> participantes = repositorioParticipante.findByPoule(poule);
        TablonPoule tablonParticipantes = tablonFactory.crearTablon(participantes.size());
        crearResultadoPoule(participantes,poule);
        return repositorioEnfrentamiento.saveAll(tablonParticipantes.generarEnfrentamientos(participantes, poule));
    }

    @Override
    public List<Enfrentamiento> verEnfrentamientosPoule(long id) {
        Poule poule = repositorioPoule.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poule no encontrada"));
        return repositorioEnfrentamiento.findByPoule(poule);
    }

    @Override
    public List<Participante> verParticipantes(long id) {
        Poule poule = repositorioPoule.findById(id).orElseThrow(() -> new ResourceNotFoundException("Poule no encontrada"));
        return repositorioParticipante.findByPoule(poule);
    }

    @Override
    public List<ResultadoPoule> verResultadoPoule(long id) {
        Poule poule = repositorioPoule.findById(id).orElseThrow(() -> new ResourceNotFoundException("Poule no encontrada"));
        return repositorioResultadoPoule.findByPoule(poule);
    }


    private List<ResultadoPoule> crearResultadoPoule(List<Participante> participantes, Poule poule) {

        List<ResultadoPoule> resultadoPoules = new ArrayList<>();
        for(Participante participante : participantes){
            ResultadoPoule resultadoPoule = new ResultadoPoule();
            resultadoPoule.setParticipante(participante);
            resultadoPoule.setPoule(poule);
            resultadoPoule.setVictorias((short) 0);
            resultadoPoule.setTocadosDiferencia((short) 0);
            repositorioResultadoPoule.save(resultadoPoule);
            resultadoPoules.add(resultadoPoule);
        }

        return resultadoPoules;
    }


}
