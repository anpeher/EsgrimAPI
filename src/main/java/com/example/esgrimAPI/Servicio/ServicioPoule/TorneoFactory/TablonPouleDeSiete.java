package com.example.esgrimAPI.Servicio.ServicioPoule.TorneoFactory;

import com.example.esgrimAPI.Modelo.Enfrentamiento.Enfrentamiento;
import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;

import java.util.ArrayList;
import java.util.List;

public class TablonPouleDeSiete implements TablonPoule{
    @Override
    public List<Enfrentamiento> generarEnfrentamientos(List<Participante> participantes, Poule poule) {
        if (participantes.size() != 7) {
            throw new IllegalArgumentException("El número de participantes debe ser 7 para este tablón.");
        }
        List<Enfrentamiento> enfrentamientos = new ArrayList<>();
        enfrentamientos.add(new Enfrentamiento(participantes.get(0), participantes.get(1), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(2), participantes.get(3), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(4), participantes.get(5), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(6), participantes.get(0), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(1), participantes.get(2), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(3), participantes.get(4), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(5), participantes.get(6), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(0), participantes.get(2), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(3), participantes.get(5), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(1), participantes.get(4), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(6), participantes.get(2), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(0), participantes.get(3), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(4), participantes.get(6), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(1), participantes.get(5), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(0), participantes.get(4), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(2), participantes.get(5), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(3), participantes.get(6), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(1), participantes.get(3), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(2), participantes.get(4), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(0), participantes.get(5), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(1), participantes.get(6), poule));

        return enfrentamientos;
    }
}
