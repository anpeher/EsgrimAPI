package com.example.esgrimAPI.Servicio.ServicioParticipante;

import com.example.esgrimAPI.DTO.Participante.CrearParticipanteDTO;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Modelo.Torneo.Torneo;
import com.example.esgrimAPI.Modelo.Usuario.Genero;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Repositorio.RepositorioParticipante;
import com.example.esgrimAPI.Repositorio.RepositorioTorneo;
import com.example.esgrimAPI.Repositorio.RepositorioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicioParticipanteApl implements ServicioParticipante{

    private RepositorioUsuario repositorioUsuario;
    private RepositorioTorneo repositorioTorneo;
    private RepositorioParticipante repositorioParticipante;

    @Override
    public Participante ApuntarseTorneo(CrearParticipanteDTO crearParticipanteDTO) {
        Torneo torneo = repositorioTorneo.findById(crearParticipanteDTO.getIdTorneo())
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        if (crearParticipanteDTO.getIdUsuario() != null){
            Usuario usuario = repositorioUsuario.findById(crearParticipanteDTO.getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
            Participante participante = obtenerParticipanteInterino(usuario, torneo);
            return repositorioParticipante.save(participante);
        }
        Participante participante = obtenerParticipante(crearParticipanteDTO, torneo);
        return repositorioParticipante.save(participante);
    }

    @Override
    public Participante verParticipante(long id) {
        return repositorioParticipante.findById(id).orElseThrow(() -> new ResourceNotFoundException("Partipante no encontrado"));
    }

    @Override
    public void descalificarParticipante(long id) {
        Participante participante = repositorioParticipante.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partipante no encontrado"));
        participante.setDescalificado(Boolean.TRUE);
        repositorioParticipante.save(participante);
    }

    @Override
    public void readmitirParticipante(long id) {
        Participante participante = repositorioParticipante.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partipante no encontrado"));
        participante.setDescalificado(Boolean.FALSE);
        repositorioParticipante.save(participante);
    }

    private Participante obtenerParticipanteInterino(Usuario usuario, Torneo torneo){

        if (usuario.getEdad() > torneo.getEdad()){
            throw new IllegalArgumentException("El usuario no puede participar debido a que supera la edad permitida.");
        }

        if (!usuario.getGenero().equals(torneo.getGenero()) ){
            throw new IllegalArgumentException("El usuario no puede participar debido a que no es para su género.");
        }

        Participante participante = new Participante();
        participante.setApellidos(usuario.getApellidos());
        participante.setNombre(usuario.getNombre());
        participante.setTorneo(torneo);
        participante.setRanking(usuario.getClasificacion());
        participante.setUsuario(usuario);
        return participante;
    }

    private Participante obtenerParticipante(CrearParticipanteDTO crearParticipanteDTO, Torneo torneo){

        if (crearParticipanteDTO.getEdad() > torneo.getEdad()){
            throw new IllegalArgumentException("El usuario no puede participar debido a que supera la edad permitida.");
        }

        if (!Genero.valueOf(crearParticipanteDTO.getGenero()).equals(torneo.getGenero()) ){
            throw new IllegalArgumentException("El usuario no puede participar debido a que no es para su género.");
        }

        Participante participante = new Participante();
        participante.setApellidos(crearParticipanteDTO.getApellidos());
        participante.setNombre(crearParticipanteDTO.getNombre());
        participante.setTorneo(torneo);
        participante.setRanking((short)0);
        return participante;
    }

}
