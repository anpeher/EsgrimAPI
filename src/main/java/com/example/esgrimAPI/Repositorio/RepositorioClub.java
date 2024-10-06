package com.example.esgrimAPI.Repositorio;
import com.example.esgrimAPI.Modelo.Club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link com.example.esgrimAPI.Modelo.Club.Club}
 */
@Repository

public interface RepositorioClub extends JpaRepository<Club, Long>{


}
