package com.example.esgrimAPI.Repositorio;


import com.example.esgrimAPI.Modelo.Notificacion.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Notificacion}
 */
@Repository
public interface RepositorioNotificacion extends JpaRepository<Notificacion, Long> {
}
