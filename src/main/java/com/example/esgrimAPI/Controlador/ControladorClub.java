package com.example.esgrimAPI.Controlador;

import com.example.esgrimAPI.DTO.Club.ClubDto;
import com.example.esgrimAPI.DTO.Respuesta.Message;
import com.example.esgrimAPI.Modelo.Club.Club;
import com.example.esgrimAPI.Servicio.ServicioClub.ServicioClubApl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/club")
@AllArgsConstructor
public class ControladorClub {

    private final ServicioClubApl servicioClub;

    @GetMapping
    public ResponseEntity obtenerInfo(){
        return new ResponseEntity<>(servicioClub.infoClub(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity modificarClub(@Valid @RequestBody ClubDto ClubDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidacionParametros.manejarValidacion(bindingResult);
        }
        try {
            Club club = servicioClub.modificarInformacionClub(ClubDto);
            return ResponseEntity.ok(club);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }



}
