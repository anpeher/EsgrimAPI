package com.example.esgrimAPI.Servicio.ServicioChat;

import com.example.esgrimAPI.DTO.Chat.CrearChatDTO;
import com.example.esgrimAPI.DTO.Chat.ModChatDTO;
import com.example.esgrimAPI.Modelo.Chat.Chat;
import com.example.esgrimAPI.Modelo.Mensaje.Mensaje;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Repositorio.RepositorioChat;
import com.example.esgrimAPI.Repositorio.RepositorioMensaje;
import com.example.esgrimAPI.Repositorio.RepositorioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ServicioChatApl implements ServicioChat {

    private RepositorioChat repositorioChat;
    private RepositorioMensaje repositorioMensaje;
    private RepositorioUsuario repositorioUsuario;
    /**
     * Crea un nuevo chat para poder enviarse mensajes.
     *
     * @param crearChatDTO el json con la informacion para crear un chat
     * @return el nuevo chat creado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     */
    @Override
    public Chat crearChat(CrearChatDTO crearChatDTO) {
        if(crearChatDTO.getUsuarioCreador() == crearChatDTO.getUsuarioParticipante()){throw new IllegalArgumentException("El id del chat creador no puede ser el mismo que el del participante.");}
        Usuario usuarioCreador = repositorioUsuario.findById(crearChatDTO.getUsuarioCreador())
                .orElseThrow(() -> new IllegalArgumentException("ServicioUsuario creador no encontrado"));
        if (!usuarioCreador.isActivo()){throw new ResourceNotFoundException("Usuario creador no encontrado");}
        Usuario usuarioParticipante = repositorioUsuario.findById(crearChatDTO.getUsuarioParticipante())
                .orElseThrow(() -> new IllegalArgumentException("ServicioUsuario participante no encontrado"));
        if (!usuarioParticipante.isActivo()){throw new ResourceNotFoundException("Usuario participante no encontrado");}
        return repositorioChat.save(obtenerChat(crearChatDTO, usuarioCreador, usuarioParticipante));

    }

    /**
     * Modifica la informacion de un chat.
     * @param id el json con la informacion para modificar un chat
     * @param modChatDTO el json con la informacion para modificar un mensaje
     * @return el nuevo chat modificado.
     * @throws IllegalArgumentException  si ha hay algún problema en la validación.
     * @throws ResourceNotFoundException si el chat no existe.
     */
    @Override
    public Chat modChat(Long id, ModChatDTO modChatDTO) {
        Chat chat = repositorioChat.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chat no encontrado"));
        chat.setTitulo(modChatDTO.getTitulo());
        return repositorioChat.save(chat);
    }

    /**
     * Devuelve un chat para ver su informacion.
     *
     * @param id el id del chat
     * @return la informacion del chat
     * @throws ResourceNotFoundException si el chat no existe.
     */
    @Override
    public Chat verChat(long id) {
        return repositorioChat.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chat no encontrado"));
    }

    /**
     * Elimina un chat.
     *
     * @param id el id del chat
     * @return la informacion del chat
     * @throws ResourceNotFoundException si el chat no existe.
     */
    @Override
    public void eliminarChat(long id) {
        Chat chat = repositorioChat.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chat no encontrado"));
        repositorioChat.delete(chat);

    }

    /**
     * Devuelve una lista de los mensajes creados en un chat.
     * @param id el id del chat.
     * @return lista de mensajes que contiene un chat.
     * @throws ResourceNotFoundException si el chat no existe.
     */
    @Override
    public List<Mensaje> MensajesChats(Long id) {
        Chat chat = repositorioChat.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chat no encontrado"));
        return repositorioMensaje.findMensajesChat(id);
    }

    /**
     * Devuelve una lista de chats creados por un usuario.
     *
     * @param id el id del usuario.
     * @return lista de chats creados por un usuario.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    @Override
    public List<Chat> chatsUsuario(Long id) {
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if (!usuario.isActivo()){throw new ResourceNotFoundException("Usuario no existe");}
        return repositorioChat.findChatsUsuarios(id);
    }

    /**
     * Crea el chat usando el json recibido y con el ususario, el cual ya se han comprobado que existe.
     * @param crearChatDTO json que contiene la informacion para crear un chat.
     * @param usuarioCreador usuario que crea el chat.
     * @param usuarioParticipante usuario que participa en el chat.
     * @return el chat creado si tod0 ha salido bien.
     */
    private Chat obtenerChat(CrearChatDTO crearChatDTO, Usuario usuarioCreador, Usuario usuarioParticipante){

        Chat chat = new Chat();
        chat.setTitulo(crearChatDTO.getTitulo());
        chat.setUsuarioCreador(usuarioCreador);
        chat.setUsuarioParticipante(usuarioParticipante);
        return chat;
    }
}
