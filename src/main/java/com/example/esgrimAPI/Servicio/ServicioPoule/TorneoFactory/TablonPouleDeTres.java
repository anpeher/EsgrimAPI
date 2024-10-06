package com.example.esgrimAPI.Servicio.ServicioPoule.TorneoFactory;

import com.example.esgrimAPI.Modelo.Enfrentamiento.Enfrentamiento;
import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Poule.Poule;

import java.util.ArrayList;
import java.util.List;

public class TablonPouleDeTres implements TablonPoule {

    @Override
    public List<Enfrentamiento> generarEnfrentamientos(List<Participante> participantes,  Poule poule) {
        if (participantes.size() != 3) {
            throw new IllegalArgumentException("El número de participantes debe ser 3 para este tablón.");
        }

        List<Enfrentamiento> enfrentamientos = new ArrayList<>();

        // Agrega lógica para crear los enfrentamientos en un tablón de 3 participantes
        enfrentamientos.add(new Enfrentamiento(participantes.get(0), participantes.get(1), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(0), participantes.get(2), poule));
        enfrentamientos.add(new Enfrentamiento(participantes.get(1), participantes.get(2), poule));

        return enfrentamientos;
    }
}
