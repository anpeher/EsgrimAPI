package com.example.esgrimAPI.Servicio.ServicioPoule;

import com.example.esgrimAPI.Modelo.Enfrentamiento.Enfrentamiento;
import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.ResultadoPoule.ResultadoPoule;

import java.util.List;

public interface ServicioPoule {


    List<Enfrentamiento> CrearEnfrentamientos(long id);

    List<Enfrentamiento> verEnfrentamientosPoule(long id);

    List<Participante> verParticipantes(long id);

    List<ResultadoPoule> verResultadoPoule(long id);


}
