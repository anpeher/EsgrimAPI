package com.example.esgrimAPI.DTO.Directa;


import com.example.esgrimAPI.Modelo.Directa.Directa;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Constructor JSON para enviar los datos de una ronda de directas
 */
@Getter
@Setter
public class RondaDirectaDTO {

    String ronda;

    List<Directa> directas;
}
