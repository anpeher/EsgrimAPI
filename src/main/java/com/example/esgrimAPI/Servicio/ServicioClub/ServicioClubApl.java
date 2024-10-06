package com.example.esgrimAPI.Servicio.ServicioClub;

import com.example.esgrimAPI.DTO.Club.ClubDto;
import com.example.esgrimAPI.Modelo.Club.Club;
import com.example.esgrimAPI.Repositorio.RepositorioClub;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class ServicioClubApl implements ServicioClub {

    final private long idClub = 1;
    private RepositorioClub repositorioClub;
    /**
     * devuelve la informacion del club
     *
     * @return informacion del club
     */
    @Override
    public Club infoClub() {
        return repositorioClub.getReferenceById(idClub);
    }

    /**
     * Modifica informacion del club existente como su email o teléfono. Utiliza el json personalizado {@link ClubDto}
     *
     * @param modClubDTO los datos para modificar el ingreso.
     * @return el ingreso modificado o un Optional vacio si no se encuentra el ingreso.
     * @throws IllegalArgumentException si la validacion es erronea.
     */
    @Override
    public Club modificarInformacionClub(ClubDto modClubDTO) {
        Club club = repositorioClub.findById(idClub).orElseThrow(() -> new IllegalArgumentException("Club no encontrado"));

        Optional.ofNullable(modClubDTO.getDireccion()).ifPresent(club::setDireccion);
        Optional.ofNullable(modClubDTO.getNombre()).ifPresent(club::setNombre);
        Optional.ofNullable(modClubDTO.getEmail()).ifPresent(club::setEmail);
        Optional.ofNullable(modClubDTO.getTelefono()).ifPresent(club::setTelefono);
        return repositorioClub.save(club);
    }
}
