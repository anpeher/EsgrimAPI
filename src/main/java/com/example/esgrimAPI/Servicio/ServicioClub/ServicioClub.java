package com.example.esgrimAPI.Servicio.ServicioClub;

import com.example.esgrimAPI.DTO.Club.ClubDto;
import com.example.esgrimAPI.Modelo.Club.Club;
;

public interface ServicioClub {

    /**
     * devuelve la informacion del club
     * @return informacion del club
     */
    Club infoClub();

    /**
     * Modifica informacion del club existente como su email o teléfono. Utiliza el json personalizado {@link ClubDto}
     * @param modClubDTO los datos para modificar el ingreso.
     * @return true si se ha modificado correctamente.
     * @throws IllegalArgumentException si la validacion es erronea.
     */
    Club modificarInformacionClub(ClubDto modClubDTO);
}
