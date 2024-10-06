package com.example.esgrimAPI.Servicio.ServicioPoule.TorneoFactory;

import org.springframework.stereotype.Component;

@Component
public class TablonFactory {

    public TablonPoule crearTablon(int numParticipantes) {
        if (numParticipantes == 7) {
            return new TablonPouleDeSiete();
        }else if (numParticipantes == 6) {
            return new TablonPouleDeSeis();
        } else if (numParticipantes == 3) {
            return new TablonPouleDeTres();
        } else {
            throw new IllegalArgumentException("No hay tablón disponible para este número de participantes.");
        }
    }
}
