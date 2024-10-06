package com.example.esgrimAPI.Repositorio;
import com.example.esgrimAPI.Modelo.Chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Chat}
 */
@Repository
public interface RepositorioChat extends JpaRepository<Chat, Long> {
    /**
     * Selecciona los chat que sean de un usuario especifico.
     * @param id el id del usuario de quien buscamos los chats.
     * @return la lista de chat.
     */
    @Query("SELECT c FROM Chat c WHERE c.usuarioCreador.id = :id OR c.usuarioParticipante.id = :id")
    List<Chat> findChatsUsuarios(Long id);
}

