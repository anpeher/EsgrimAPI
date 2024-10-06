package com.example.esgrimAPI.Servicio.ServicioMensaje;

import com.example.esgrimAPI.DTO.Mensaje.CrearMensajeDTO;
import com.example.esgrimAPI.DTO.Mensaje.ModMensajeDTO;
import com.example.esgrimAPI.Modelo.Chat.Chat;
import com.example.esgrimAPI.Modelo.Mensaje.Mensaje;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Repositorio.RepositorioChat;
import com.example.esgrimAPI.Repositorio.RepositorioMensaje;
import com.example.esgrimAPI.Repositorio.RepositorioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicioMensajeApl implements ServicioMensaje{

    private RepositorioMensaje repositorioMensaje;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioChat repositorioChat;
    /**
     * Crea un nuevo mensaje para un chat determinado
     *
     * @param crearMensajeDTO el json con la informacion para crear un mensaje
     * @return el nuevo mensaje creado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación
     */
    @Override
    public Mensaje crearMensaje(CrearMensajeDTO crearMensajeDTO) {
        Usuario usuario = repositorioUsuario.findById(crearMensajeDTO.getUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        if (!usuario.isActivo()){throw new IllegalArgumentException("Usuario no existe");}
        Chat chat = repositorioChat.findById(crearMensajeDTO.getChat())
                .orElseThrow(() -> new IllegalArgumentException("Chat no encontrado"));
        return repositorioMensaje.save(obtenerMensaje(crearMensajeDTO, usuario, chat));
    }

    /**
     * Modifica el contenido de un mensaje.
     *
     * @param modMensajeDTO el json con la informacion para modificar un mensaje
     * @return el nuevo mensaje meodificado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación
     * @throws ResourceNotFoundException si no encuentra el mensaje.
     */
    @Override
    public Mensaje modMensaje(ModMensajeDTO modMensajeDTO, Long id) {
        Mensaje mensaje = repositorioMensaje.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mensaje no encontrado"));
        mensaje.setContenido(modMensajeDTO.getContenido());
        return repositorioMensaje.save(mensaje);
    }

    /**
     * Devuelve un mensaje para ver su informacion.
     *
     * @param id el id del mensaje
     * @return la informacion del mensaje
     * @throws ResourceNotFoundException si el mensaje no existe.
     */
    @Override
    public Mensaje verMensaje(long id) {
        return repositorioMensaje.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Mensaje no existe"));
    }

    /**
     * Elimina un mensaje.
     *
     * @param id el id del mensaje
     * @return la informacion del mensaje
     * @throws ResourceNotFoundException si el mensaje no existe.
     */
    @Override
    public void eliminarMensaje(long id) {
        Mensaje mensaje = repositorioMensaje.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Mensaje no existe"));
        repositorioMensaje.delete(mensaje);
    }

    /**
     * Devuelve una lista de mensajes creados por un usuario.
     *
     * @param id el id del usuario.
     * @return lista de mensajes creados por un usuario.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    @Override
    public List<Mensaje> mensajesUsuario(Long id) {
        return repositorioMensaje.findMensajesUsuarios(id);
    }

    /**
     * Crea el mensaje usando el json recibido y con el ususario y chat, los cuales ya se han comprobado que existen.
     * @param crearMensajeDTO json que contiene la informacion para crear un mensaje.
     * @param usuario usuario que crea el mensaje.
     * @param chat chat donde se envía la informacion.
     * @return el mensaje creado si tod0 ha salido bien.
     */
    private Mensaje obtenerMensaje(CrearMensajeDTO crearMensajeDTO, Usuario usuario, Chat chat){

        Mensaje mensaje = new Mensaje();
        mensaje.setContenido(crearMensajeDTO.getContenido());
        mensaje.setUsuario(usuario);
        mensaje.setChat(chat);
        return mensaje;
    }
}
