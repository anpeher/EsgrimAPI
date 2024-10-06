package com.example.esgrimAPI.Servicio.ServicioChat;

import com.example.esgrimAPI.DTO.Chat.CrearChatDTO;
import com.example.esgrimAPI.DTO.Chat.ModChatDTO;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Chat.Chat;
import com.example.esgrimAPI.Modelo.Mensaje.Mensaje;

import java.util.List;

/**
 * Interfaz del servicio de chat donde se definen todas sus operaciones.
 */
public interface ServicioChat {

    /**
     * Crea un nuevo chat para poder enviarse mensajes.
     * @param crearChatDTO el json con la informacion para crear un chat
     * @return el nuevo chat creado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     */
    Chat crearChat(CrearChatDTO crearChatDTO);

    /**
     * Modifica la informacion de un chat.
     * @param modChatDTO el json con la informacion para modificar un chat
     * @param id el json con la informacion para modificar un chat
     * @return el nuevo chat modificado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws ResourceNotFoundException si el chat no existe.
     */
    Chat modChat(Long id, ModChatDTO modChatDTO);

    /**
     * Devuelve un chat para ver su informacion.
     * @param id el id del chat
     * @return la informacion del chat
     * @throws ResourceNotFoundException si el chat no existe.
     */
    Chat verChat(long id);

    /**
     * Elimina un chat.
     * @param id el id del chat
     * @return la informacion del chat
     * @throws ResourceNotFoundException si el chat no existe.
     */
    void eliminarChat(long id);

    /**
     * Devuelve una lista de los mensajes creados en un chat.
     * @param id el id del chat.
     * @return lista de mensajes que contiene un chat.
     * @throws ResourceNotFoundException si el chat no existe.
     */
    List<Mensaje> MensajesChats(Long id);

    /**
     * Devuelve una lista de chats creados por un usuario.
     * @param id el id del usuario.
     * @return lista de chats creados por un usuario.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    List<Chat> chatsUsuario(Long id);

}
