package com.example.esgrimAPI.Repositorio;

import com.example.esgrimAPI.Modelo.Inventario.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Inventario}
 */
@Repository
public interface RepositorioInventario  extends JpaRepository<Inventario, Long> {
}
