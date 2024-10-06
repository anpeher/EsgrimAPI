package com.example.esgrimAPI.Repositorio;

import com.example.esgrimAPI.Modelo.Clase.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Clase}
 */
@Repository
public interface RepositorioClase extends JpaRepository<Clase, Long> {

    /**
     * buscamos todas las clases impartidas por un usuario determinado.
     * @param id el id del usuario que buscamos.
     * @return una lista con las clases que da el usuario.
     */
    List<Clase> findByMaestro_Id(long id);
}
