package com.example.esgrimAPI.Servicio.ServicioNotificacion;


import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionDTO;
import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionGrupalDTO;
import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionSMS;
import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionSMSGrupal;
import com.example.esgrimAPI.Modelo.Notificacion.Notificacion;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Excepciones.SmsException;
import jakarta.mail.MessagingException;

import java.util.List;

public interface ServicioNotificacion {

    /**
     * Crea una nueva notificacion para enviar a un usuario
     * @param notificacion el json con la informacion para crear una notificacion
     * @return la nueva notificacion creada.
     * @throws IllegalArgumentException si hay algún problema en la validación.
     * @throws MessagingException  si hay algún problema a la hora de enviar un correo.
     */
    Notificacion crearNotificacionPersonal(CrearNotificacionDTO notificacion, long id) throws MessagingException;
    /**
     * Crea una nueva notificacion para enviar a varios usuarios.
     * @param notificacion el json con la informacion para crear una notificacion
     * @return la nueva notificacion creada.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws MessagingException  si hay algún problema a la hora de enviar un correo.
     */
    List<Usuario> crearNotificacionGrupal(CrearNotificacionGrupalDTO notificacion) throws MessagingException;
    /**
     * Crea una nueva notificacion para enviar a los alumnos de una clase.
     * @param notificacion el json con la informacion para crear una notificacion
     * @return la nueva notificacion creada.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws ResourceNotFoundException si no existe la clase.
     * @throws MessagingException  si hay algún problema a la hora de enviar un correo.
     */
    List<Usuario> crearNotificacionClase(CrearNotificacionDTO notificacion, long id) throws MessagingException;
    /**
     * Crea una nueva notificacion para a un rol especifico.
     * @param notificacion el json con la informacion para crear una notificacion
     * @return la nueva notificacion creada.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws MessagingException  si hay algún problema a la hora de enviar un correo.
     */
    List<Usuario> crearNotificacionRol(CrearNotificacionGrupalDTO notificacion) throws MessagingException;

    /**
     * eniva una notifcacion por SMS
     * @param crearNotificacionSMS el DTO donde viene la informacion para enviar el SMS.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws SmsException si ha hay algún problema a la hora de enviar el SMS.
     */
    void crearNotificacionSMS(CrearNotificacionSMS crearNotificacionSMS);

    /**
     * eniva una notifcacion por SMS a varios usuarios
     * @param crearNotificacionSMSGrupal el DTO donde viene la informacion para enviar los SMS.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws SmsException si ha hay algún problema a la hora de enviar el SMS.
     */
    List<Usuario> crearNotificacionSMSGrupal(CrearNotificacionSMSGrupal crearNotificacionSMSGrupal);

    /**
     * Busca todas las notificaciones enviadas.
     * @return una lista de notificaciones.
     */
    List<Notificacion> verNotificaciones();

    /**
     * Busca una notificacion especifica.
     * @param id el id de la notificacion.
     * @return la notificacion deseada.
     * @throws ResourceNotFoundException si no existe la notificacion.
     */
    Notificacion verNotificacion(long id);


    //para participantes torneo
}
