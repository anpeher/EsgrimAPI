package com.example.esgrimAPI.Servicio.ServicioDirecta;

import com.example.esgrimAPI.DTO.Directa.ResultadoDirectaDTO;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Club.Club;
import com.example.esgrimAPI.Modelo.Directa.Directa;
import com.example.esgrimAPI.Modelo.Participante.Participante;
import com.example.esgrimAPI.Repositorio.RepositorioDirecta;
import com.example.esgrimAPI.Repositorio.RepositorioParticipante;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicioDirectaApl implements ServicioDirecta{

    private RepositorioDirecta repositorioDirecta;
    private RepositorioParticipante repositorioParticipante;


    @Override
    public Directa resultadoDirecta(long id, ResultadoDirectaDTO resultadoDirectaDTO) {
        Directa directa = repositorioDirecta.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Directa no encontrada"));
        return repositorioDirecta.save(crearDirecta(directa,resultadoDirectaDTO));
    }

    @Override
    public Directa verDirecta(long id) {
        return repositorioDirecta.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Directa no encontrada"));
    }

    private Directa crearDirecta(Directa directa, ResultadoDirectaDTO resultadoDirectaDTO){

        directa.setResultadoTirador1((short) resultadoDirectaDTO.getResultado1());
        directa.setResultadoTirador2((short) resultadoDirectaDTO.getResultado2());
        Participante tirador1 = directa.getTirador1();
        Participante tirador2 = directa.getTirador2();
        if(directa.getResultadoTirador1() > directa.getResultadoTirador2()){
            tirador2.setDescalificado(Boolean.TRUE);
            if ( tirador2.getRanking() < tirador1.getRanking()) {
                short rankingMenor = tirador1.getRanking();
                tirador1.setRanking(tirador2.getRanking());
                tirador2.setRanking(rankingMenor);
                repositorioParticipante.save(tirador1);
            }
            repositorioParticipante.save(tirador2);

        }

        if(directa.getResultadoTirador2() > directa.getResultadoTirador1()){
            tirador1.setDescalificado(Boolean.TRUE);
            if(tirador1.getRanking() < tirador2.getRanking()) {
                short rankingMenor = tirador2.getRanking();
                tirador2.setRanking(tirador1.getRanking());
                tirador1.setRanking(rankingMenor);
                repositorioParticipante.save(tirador2);
            }
            repositorioParticipante.save(tirador1);
        }

        return directa;
    }
}
