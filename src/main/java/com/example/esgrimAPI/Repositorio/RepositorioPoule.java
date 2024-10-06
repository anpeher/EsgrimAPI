package com.example.esgrimAPI.Repositorio;


import com.example.esgrimAPI.Modelo.Poule.Poule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Poule}
 */
@Repository
public interface RepositorioPoule extends JpaRepository<Poule, Long> {


}
