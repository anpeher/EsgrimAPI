package com.example.esgrimAPI.Servicio.ServicioPoule.TorneoFactory;

import com.example.esgrimAPI.Modelo.Enfrentamiento.Enfrentamiento;
import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;

import java.util.List;

public interface TablonPoule {

    List<Enfrentamiento> generarEnfrentamientos(List<Participante> participantes, Poule poule);
}
