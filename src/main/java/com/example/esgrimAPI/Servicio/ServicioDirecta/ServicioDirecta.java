package com.example.esgrimAPI.Servicio.ServicioDirecta;

import com.example.esgrimAPI.DTO.Directa.ResultadoDirectaDTO;
import com.example.esgrimAPI.Modelo.Directa.Directa;

public interface ServicioDirecta {

    Directa resultadoDirecta(long id, ResultadoDirectaDTO resultadoDirectaDTO);

    Directa verDirecta(long id);
}
