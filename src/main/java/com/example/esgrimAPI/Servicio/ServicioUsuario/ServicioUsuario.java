package com.example.esgrimAPI.Servicio.ServicioUsuario;

import com.example.esgrimAPI.DTO.Usuario.ContraseñaUsuario;
import com.example.esgrimAPI.DTO.Usuario.CrearUsuarioDTO;
import com.example.esgrimAPI.DTO.Usuario.ModUsuarioDTO;
import com.example.esgrimAPI.DTO.Usuario.Respuestas.UsuarioClasificacionDTO;
import com.example.esgrimAPI.DTO.Usuario.RolUsuario;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Clase.Clase;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;

import java.util.List;


/**
 * Interfaz del servicio de usuario donde se definen todas sus operaciones.
 */
public interface ServicioUsuario {

    /**
     * Crea un nuevo usuario para el club.
     * @param crearUsuarioDTO el json con la informacion para crear un usuario
     * @return el nuevo usuario creado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación
     */
    Usuario crearUsuario(CrearUsuarioDTO crearUsuarioDTO);

    /**
     * Modifica el contenido de un usuario.
     * @param modUsuarioDTO el json con la informacion para modificar un usuario.
     * @param id es el id del usuario para modificar.
     * @return el nuevo usuario modificado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación
     */
    Usuario modUsuario(ModUsuarioDTO modUsuarioDTO, Long id);

    /**
     * Devuelve un usuario para ver su informacion.
     * @param id el id del usuario
     * @return la informacion del usuario
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    Usuario verUsuario(long id);

    /**
     * Elimina un usuario.
     * @param id el id del usuario
     * @return la informacion del usuario
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    void eliminarUsuario(long id);

    /**
     * Muestra una lista completa de todos los usuarios.
     * @return lista de usuarios.
     */
    List<Usuario> verUsuarios();

    /**
     * Muestra los usuarios ordenados por orden de clasificación, en caso de empate por puntos se mostrará por orden de apellidos, pero ambos tendrán la misma posicion
     * @return una lista de usuarios ordenados por orden de clasificacion.
     */
    List<UsuarioClasificacionDTO> verUsuariosPorOrdenDeClasificacion();

    /**
     * Reseta la puntuación de todos los participantes haciendo que su puntuacion sea 0.
     */
    void resetearPuntuacion();

    /**
     * Permite a un usuario apuntarse a una clase.
     * @param idUsuario es el id del usuario que se quiere apuntar a la clase.
     * @param idClase es el id de la clase a la que se va a apuntar.
     * @return la clase a la que se ha apuntado el usuario.
     * @throws ResourceNotFoundException si el usuario o la clase no existe.
     */
    Clase apuntarseClase(long idUsuario, long idClase);

    /**
     * Cambia a un usuario a activo.
     * @param id el id del usuario.
     * @return el usuario reactivado.
     * @throws IllegalArgumentException si el usuario ya esta activo.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    Usuario reactivarUsuario(long id);

    /**
     * Cambia el rol del usuario.
     * @param id el id del usuario.
     * @param rolUsuario el json con el rol del usuario.
     * @return el usuario con su nuevo rol.
     * @throws IllegalArgumentException si hay errores en la validación.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    Usuario cambiarRol(long id, RolUsuario rolUsuario);

    /**
     * Cambia la contraseña de un alumno.
     * @param id el id del usuario.
     * @param contraseñaUsuario el json con la contrasena del usuario.
     * @return el usuario reactivado.
     * @throws IllegalArgumentException si hay errores en la validación.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    void cambiarContrasena(long id, ContraseñaUsuario contraseñaUsuario);


}
