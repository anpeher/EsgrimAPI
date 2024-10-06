package com.example.esgrimAPI.Servicio.ServicioNotificacion;


import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionDTO;
import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionGrupalDTO;
import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionSMS;
import com.example.esgrimAPI.DTO.Notificacion.CrearNotificacionSMSGrupal;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Notificacion.Notificacion;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Repositorio.RepositorioClase;
import com.example.esgrimAPI.Repositorio.RepositorioNotificacion;
import com.example.esgrimAPI.Repositorio.RepositorioUsuario;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.esgrimAPI.Excepciones.SmsException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServicioNotificacionApl implements ServicioNotificacion{

    private RepositorioUsuario repositorioUsuario;
    private RepositorioClase repositorioClase;
    private RepositorioNotificacion repositorioNotificacion;
    @Autowired
    private ServicioEnvioEmail servicioEnvioEmail;
    @Autowired
    private ServicioSms servicioSms;


    /**
     * Crea una nueva notificacion para enviar a un usuario
     *
     * @param crearNotificacionDTO el json con la informacion para crear una notificacion
     * @param id el id del usuario a quien se le envía el mensaje.
     * @return la nueva notificacion creada.
     * @throws IllegalArgumentException  si hay algún problema en la validación.
     * @throws MessagingException  si hay algún problema a la hora de enviar un correo.
     */
    @Override
    public Notificacion crearNotificacionPersonal(CrearNotificacionDTO crearNotificacionDTO, long id) throws MessagingException {
        Usuario usuario = comprobarUsuarioExiste(crearNotificacionDTO.getUsuario());
        Notificacion notificacion = obtenerNotificacion(crearNotificacionDTO, usuario);
        servicioEnvioEmail.sendSimpleEmail(usuario.getEmail(), notificacion.getTitulo(), notificacion.getContenido());
        return repositorioNotificacion.save(notificacion);

    }

    /**
     * Crea una nueva notificacion para enviar a varios usuarios.
     *
     * @param crearNotificacionGrupal el json con la informacion para crear una notificacion
     * @return la nueva notificacion creada.
     * @throws IllegalArgumentException  si ha hay algún problema en la validación.
     * @throws MessagingException  si hay algún problema a la hora de enviar un correo.
     */
    @Override
    public List<Usuario> crearNotificacionGrupal(CrearNotificacionGrupalDTO crearNotificacionGrupal) throws MessagingException{
        if(crearNotificacionGrupal.getUsuarios() == null){
            throw new IllegalArgumentException("la lista de usuarios no puede ser nula");
        }
        Usuario usuario = comprobarUsuarioExiste(crearNotificacionGrupal.getUsuario());
        Notificacion notificacion = obtenerNotificacionGrupal(crearNotificacionGrupal,usuario);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        for(Long id : crearNotificacionGrupal.getUsuarios())
        {
            Usuario usuarioList = comprobarUsuarioExiste(id);
            servicioEnvioEmail.sendSimpleEmail(usuarioList.getEmail(), notificacion.getTitulo(), notificacion.getContenido());
            usuarios.add(usuarioList);
        }
        return usuarios;
    }

    /**
     * Crea una nueva notificacion para enviar a los alumnos de una clase.
     *
     * @param crearNotificacion el json con la informacion para crear una notificacion
     * @param id id de la clase
     * @return la nueva notificacion creada.
     * @throws IllegalArgumentException  si ha hay algún problema en la validación.
     * @throws ResourceNotFoundException si no existe la clase.
     * @throws MessagingException  si hay algún problema a la hora de enviar un correo.
     */
    @Override
    public List<Usuario> crearNotificacionClase(CrearNotificacionDTO crearNotificacion, long id) throws MessagingException{

        Usuario usuario = comprobarUsuarioExiste(crearNotificacion.getUsuario());
        Notificacion notificacion = obtenerNotificacion(crearNotificacion, usuario);
        repositorioClase.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no existe"));
        return EnviarCorreoUsuarios(repositorioUsuario.findUsuariosClaseActivos(id), notificacion);
    }

    /**
     * Crea una nueva notificacion para a un rol especifico.
     *
     * @param crearNotificacionGrupal el json con la informacion para crear una notificacion
     * @return la nueva notificacion creada.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws MessagingException  si hay algún problema a la hora de enviar un correo.
     */
    @Override
    public List<Usuario> crearNotificacionRol(CrearNotificacionGrupalDTO crearNotificacionGrupal) throws MessagingException {
        if(crearNotificacionGrupal.getRol() == null){
            throw new IllegalArgumentException("el rol no puede ser nulo");
        }
        Usuario usuario  = comprobarUsuarioExiste(crearNotificacionGrupal.getUsuario());
        Notificacion notificacion = obtenerNotificacionGrupal(crearNotificacionGrupal,usuario);
        return EnviarCorreoUsuarios(repositorioUsuario.findByRolAndActivoTrue(crearNotificacionGrupal.getRol()), notificacion);
    }

    /**
     * eniva una notifcacion por SMS a varios usuarios
     *
     * @param crearNotificacionSMS el DTO donde viene la informacion para enviar el SMS.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws SmsException             si ha hay algún problema a la hora de enviar el SMS.
     */
    @Override
    public void crearNotificacionSMS(CrearNotificacionSMS crearNotificacionSMS) {
        Usuario usuario = comprobarUsuarioExiste(crearNotificacionSMS.getUsuario());
        Usuario usuarioReceptor = comprobarUsuarioExiste(crearNotificacionSMS.getUsuarioReceptor());
        Notificacion notificacion = obtenerNotificacionSMS(crearNotificacionSMS, usuario);
        servicioSms.sendSms("+34"+usuarioReceptor.getTelefono(), notificacion.getContenido());
        repositorioNotificacion.save(notificacion);
    }

    /**
     * eniva una notifcacion por SMS a varios usuarios
     *
     * @param crearNotificacionSMSGrupal el DTO donde viene la informacion para enviar los SMS.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws SmsException             si ha hay algún problema a la hora de enviar el SMS.
     */
    @Override
    public List<Usuario> crearNotificacionSMSGrupal(CrearNotificacionSMSGrupal crearNotificacionSMSGrupal) {

        Usuario usuario = comprobarUsuarioExiste(crearNotificacionSMSGrupal.getUsuario());
        Notificacion notificacion = obtenerNotificacionSMSGrupal(crearNotificacionSMSGrupal,usuario);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        for(Long id : crearNotificacionSMSGrupal.getUsuarios())
        {
            Usuario usuarioList = comprobarUsuarioExiste(id);
            servicioSms.sendSms("+34"+usuarioList.getTelefono(), notificacion.getContenido());
        }

        repositorioNotificacion.save(notificacion);
        return usuarios;

    }

    /**
     * Busca todas las notificaciones enviadas.
     *
     * @return una lista de notificaciones.
     */
    @Override
    public List<Notificacion> verNotificaciones() {
        return repositorioNotificacion.findAll();
    }

    /**
     * Busca una notificacion especifica.
     * @param id el id de la notificacion.
     * @return la notificacion deseada.
     * @throws ResourceNotFoundException si no existe la notificacion.
     */
    @Override
    public Notificacion verNotificacion(long id) {
        return repositorioNotificacion.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La clase no existe"));
    }

    private Notificacion obtenerNotificacion(CrearNotificacionDTO crearNotificacionDTO, Usuario usuario){

        Notificacion notificacion = new Notificacion();
        notificacion.setContenido(crearNotificacionDTO.getContenido());
        notificacion.setTitulo(crearNotificacionDTO.getTitulo());
        notificacion.setUsuario(usuario);
        return notificacion;
    }

    private Notificacion obtenerNotificacionGrupal(CrearNotificacionGrupalDTO notificacionGrupal, Usuario usuario){

        Notificacion notificacion = new Notificacion();
        notificacion.setContenido(notificacionGrupal.getContenido());
        notificacion.setTitulo(notificacionGrupal.getTitulo());
        notificacion.setUsuario(usuario);
        return notificacion;
    }

    /**
     * comprueba si el usuario existe y si no salta una excepcion.
     * @param id el id del usuario a comprobar.
     * @return el usuario si existe.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    private Usuario comprobarUsuarioExiste(long id){
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
        if (!usuario.isActivo()){throw new IllegalArgumentException("Usuario no existe");}
        return usuario;
    }

    /**
     * Envia mensajes a una lista de usuarios
     * @param usuarios la lista de usuarios
     * @param notificacion el mensaje a enviar
     * @return la lista de usuarios a los que se la ha mandado el correo.
     */
    private  List<Usuario> EnviarCorreoUsuarios(List<Usuario> usuarios, Notificacion notificacion){
        for (Usuario usuarioCorreo : usuarios){
            servicioEnvioEmail.sendSimpleEmail(usuarioCorreo.getEmail(), notificacion.getTitulo(), notificacion.getContenido());
        }
        return usuarios;
    }
    private Notificacion obtenerNotificacionSMS(CrearNotificacionSMS crearNotificacionSMS, Usuario usuario){

        Notificacion notificacion = new Notificacion();
        notificacion.setContenido(crearNotificacionSMS.getContenido());
        notificacion.setTitulo("Notificacion enviada por SMS.");
        notificacion.setUsuario(usuario);
        return notificacion;
    }

    private Notificacion obtenerNotificacionSMSGrupal(CrearNotificacionSMSGrupal crearNotificacionSMSGrupal, Usuario usuario){

        Notificacion notificacion = new Notificacion();
        notificacion.setContenido(crearNotificacionSMSGrupal.getContenido());
        notificacion.setTitulo("Notificacion enviada por SMS.");
        notificacion.setUsuario(usuario);
        return notificacion;
    }
}
