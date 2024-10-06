package com.example.esgrimAPI.Repositorio;
import com.example.esgrimAPI.Modelo.Mensaje.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Mensaje}
 */
@Repository
public interface RepositorioMensaje extends JpaRepository<Mensaje, Long> {
    /**
     * Selecciona los mensajes que sean de un usuario especifico.
     * @param id el id del usuario de quienes buscamos mensajes.
     * @return la lista de mensajes.
     */
    @Query("SELECT m FROM Mensaje m WHERE m.usuario.id = :id")
    List<Mensaje> findMensajesUsuarios(Long id);

    /**
     * Selecciona los mensajes que sean de un chat especifico.
     * @param id el id del chat de quienes buscamos mensajes.
     * @return la lista de mensajes.
     */
    @Query("SELECT m FROM Mensaje m WHERE m.chat.id = :id")
    List<Mensaje> findMensajesChat(Long id);
}
