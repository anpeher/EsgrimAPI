package com.example.esgrimAPI.Servicio.ServicioParticipante;

import com.example.esgrimAPI.DTO.Participante.CrearParticipanteDTO;
import com.example.esgrimAPI.Modelo.Participante.Participante;


public interface ServicioParticipante {

    Participante ApuntarseTorneo(CrearParticipanteDTO crearParticipanteDTO);

    Participante verParticipante(long id);

    void descalificarParticipante(long id);

    void readmitirParticipante(long id);
}
