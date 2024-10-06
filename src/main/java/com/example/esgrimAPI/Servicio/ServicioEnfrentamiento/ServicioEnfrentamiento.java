package com.example.esgrimAPI.Servicio.ServicioEnfrentamiento;

import com.example.esgrimAPI.DTO.Enfrentamiento.EnfrentamientoResultadoDTO;
import com.example.esgrimAPI.Modelo.Enfrentamiento.Enfrentamiento;

public interface ServicioEnfrentamiento {

    Enfrentamiento resultadoEnfrentamiento(long id, EnfrentamientoResultadoDTO enfrentamientoResultadoDTO);

    Enfrentamiento verEnfrentamiento(long id);

}
