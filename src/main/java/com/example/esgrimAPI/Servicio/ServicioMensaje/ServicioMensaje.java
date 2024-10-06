package com.example.esgrimAPI.Servicio.ServicioMensaje;

import com.example.esgrimAPI.DTO.Mensaje.CrearMensajeDTO;
import com.example.esgrimAPI.DTO.Mensaje.ModMensajeDTO;
import com.example.esgrimAPI.Modelo.Mensaje.Mensaje;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;

import java.util.List;

/**
 * Interfaz del servicio de mensaje donde se definen todas sus operaciones.
 */
public interface ServicioMensaje {

    /**
     * Crea un nuevo mensaje para un chat determinado
     * @param crearMensajeDTO el json con la informacion para crear un mensaje
     * @return el nuevo mensaje creado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación
     */
    Mensaje crearMensaje(CrearMensajeDTO crearMensajeDTO);

    /**
     * Modifica el contenido de un mensaje.
     * @param modMensajeDTO el json con la informacion para modificar un mensaje.
     * @param id es el id del mensajes para modificar.
     * @return el nuevo mensaje modificado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación
     */
    Mensaje modMensaje(ModMensajeDTO modMensajeDTO, Long id);

    /**
     * Devuelve un mensaje para ver su informacion.
     * @param id el id del mensaje
     * @return la informacion del mensaje
     * @throws ResourceNotFoundException si el mensaje no existe.
     */
    Mensaje verMensaje(long id);

    /**
     * Elimina un mensaje.
     * @param id el id del mensaje
     * @return la informacion del mensaje
     * @throws ResourceNotFoundException si el mensaje no existe.
     */
    void eliminarMensaje(long id);

    /**
     * Devuelve una lista de mensajes creados por un usuario.
     * @param id el id del usuario.
     * @return lista de mensajes creados por un usuario.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    List<Mensaje> mensajesUsuario(Long id);

}
