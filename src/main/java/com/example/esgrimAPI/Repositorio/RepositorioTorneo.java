package com.example.esgrimAPI.Repositorio;


import com.example.esgrimAPI.Modelo.Torneo.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Torneo}
 */
@Repository
public interface RepositorioTorneo extends JpaRepository<Torneo, Long> {
}
