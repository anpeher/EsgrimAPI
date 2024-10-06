package com.example.esgrimAPI.Repositorio;

import com.example.esgrimAPI.Modelo.Enfrentamiento.Enfrentamiento;
import com.example.esgrimAPI.Modelo.Poule.Poule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Enfrentamiento}
 */
@Repository
public interface RepositorioEnfrentamiento extends JpaRepository<Enfrentamiento, Long> {

    List<Enfrentamiento> findByPoule(Poule poule);

}
