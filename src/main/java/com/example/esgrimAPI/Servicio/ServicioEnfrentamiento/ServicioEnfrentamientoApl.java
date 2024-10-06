package com.example.esgrimAPI.Servicio.ServicioEnfrentamiento;

import com.example.esgrimAPI.DTO.Enfrentamiento.EnfrentamientoResultadoDTO;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Enfrentamiento.Enfrentamiento;
import com.example.esgrimAPI.Modelo.ResultadoPoule.ResultadoPoule;
import com.example.esgrimAPI.Repositorio.RepositorioEnfrentamiento;
import com.example.esgrimAPI.Repositorio.RepositorioResultadoPoule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicioEnfrentamientoApl implements ServicioEnfrentamiento{

    private RepositorioEnfrentamiento repositorioEnfrentamiento;
    private RepositorioResultadoPoule repositorioResultadoPoule;

    @Override
    public Enfrentamiento resultadoEnfrentamiento(long id, EnfrentamientoResultadoDTO enfrentamientoResultadoDTO) {
        Enfrentamiento enfrentamiento = repositorioEnfrentamiento.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Enfrentamiento no encontrado"));

        return repositorioEnfrentamiento.save(crearEnfrentamiento(enfrentamiento, enfrentamientoResultadoDTO));
    }

    @Override
    public Enfrentamiento verEnfrentamiento(long id) {
        return repositorioEnfrentamiento.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Enfrentamiento no encontrado"));
    }

    private Enfrentamiento crearEnfrentamiento(Enfrentamiento enfrentamiento, EnfrentamientoResultadoDTO enfrentamientoResultadoDTO){

        enfrentamiento.setResultadoTirador1((short) enfrentamientoResultadoDTO.getResultado1());
        enfrentamiento.setResultadoTirador2((short) enfrentamientoResultadoDTO.getResultado2());
        ResultadoPoule resultadoPoule = repositorioResultadoPoule.findByParticipante(enfrentamiento.getTirador1());
        resultadoPoule.setTocadosDiferencia((short) (resultadoPoule.getTocadosDiferencia() + enfrentamiento.getResultadoTirador1() - enfrentamiento.getResultadoTirador2()));
        if (enfrentamientoResultadoDTO.getResultado1() > enfrentamiento.getResultadoTirador2()){
            resultadoPoule.setVictorias((short) (resultadoPoule.getVictorias()+1));
        }
        repositorioResultadoPoule.save(resultadoPoule);
        resultadoPoule = repositorioResultadoPoule.findByParticipante(enfrentamiento.getTirador2());
        resultadoPoule.setTocadosDiferencia((short) (enfrentamiento.getResultadoTirador2() - enfrentamiento.getResultadoTirador1()));
        if (enfrentamientoResultadoDTO.getResultado2() > enfrentamiento.getResultadoTirador1()){
            resultadoPoule.setVictorias((short) (resultadoPoule.getVictorias()+1));
        }
        repositorioResultadoPoule.save(resultadoPoule);
        return enfrentamiento;
    }
}
