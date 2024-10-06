package com.example.esgrimAPI.Repositorio;

import com.example.esgrimAPI.Modelo.Directa.Directa;
import com.example.esgrimAPI.Modelo.Torneo.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
    * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Directa}
 */
@Repository
public interface RepositorioDirecta extends JpaRepository<Directa, Long> {

    List <Directa> findByTorneo(Torneo torneo);


}
