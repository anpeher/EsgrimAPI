package com.example.esgrimAPI.Servicio.ServicioUsuario;

import com.example.esgrimAPI.DTO.Usuario.ContraseñaUsuario;
import com.example.esgrimAPI.DTO.Usuario.CrearUsuarioDTO;
import com.example.esgrimAPI.DTO.Usuario.ModUsuarioDTO;
import com.example.esgrimAPI.DTO.Usuario.Respuestas.UsuarioClasificacionDTO;
import com.example.esgrimAPI.DTO.Usuario.RolUsuario;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Clase.Clase;
import com.example.esgrimAPI.Modelo.Club.Club;
import com.example.esgrimAPI.Modelo.Usuario.Genero;
import com.example.esgrimAPI.Modelo.Usuario.Rol;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Repositorio.RepositorioClase;
import com.example.esgrimAPI.Repositorio.RepositorioClub;
import com.example.esgrimAPI.Repositorio.RepositorioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioUsuarioApl implements ServicioUsuario, UserDetailsService {

    private final long idClub = 1;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioClub repositorioclub;
    private RepositorioClase repositorioClase;

    /**
     * Crea un nuevo usuario para poder enviarse mensajes.
     *
     * @param crearUsuarioDTO el json con la informacion para crear un usuario
     * @return el nuevo usuario creado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     */
    @Override
    public Usuario crearUsuario(CrearUsuarioDTO crearUsuarioDTO) {
        Club club = repositorioclub.findById(idClub)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return repositorioUsuario.save(obtenerUsuario(crearUsuarioDTO, club));
    }

    /**
     * Modifica el contenido de un usuario.
     *
     * @param modUsuarioDTO el json con la informacion para modificar un usuario.
     * @param id es el id del usuario para modificar.
     * @return el nuevo usuario modificado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    @Override
    public Usuario modUsuario(ModUsuarioDTO modUsuarioDTO, Long id) {
        Usuario usuario = comprobarUsuarioExiste(id);
        Optional.ofNullable(modUsuarioDTO.getNombre()).ifPresent(usuario::setNombre);
        Optional.ofNullable(modUsuarioDTO.getApellidos()).ifPresent(usuario::setApellidos);
        Optional.ofNullable(modUsuarioDTO.getEmail()).ifPresent(usuario::setEmail);
        Optional.ofNullable(modUsuarioDTO.getGenero())
                .map(Genero::valueOf)
                .ifPresent(usuario::setGenero);
        Optional.ofNullable(modUsuarioDTO.getTelefono()).ifPresent(usuario::setTelefono);
        return repositorioUsuario.save(usuario);
    }


    /**
     * Devuelve un usuario para ver su informacion.
     *
     * @param id el id del usuario
     * @return la informacion del usuario
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    @Override
    public Usuario verUsuario(long id) {
        return comprobarUsuarioExiste(id);
    }

    /**
     * Elimina un usuario.
     *
     * @param id el id del usuario
     * @return la informacion del usuario
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    @Override
    public void eliminarUsuario(long id) {
        Usuario usuario = comprobarUsuarioExiste(id);
        List<Clase> clases = repositorioClase.findByMaestro_Id(id);
        clases.stream().forEach(c -> c.setMaestro(null));
        repositorioClase.saveAll(clases);
        usuario.setActivo(false);
        repositorioUsuario.save(usuario);
    }

    /**
     * Muestra una lista completa de todos los usuarios.
     *
     * @return lista de usuarios.
     */
    @Override
    public List<Usuario> verUsuarios() {
        return repositorioUsuario.findAllByActivoTrue();
    }

    /**
     * Muestra los usuarios ordenados por orden de clasificación, en caso de empate por puntos se mostrará por orden de apellidos, pero ambos tendrán la misma posicion
     *
     * @return una lista de usuarios ordenados por orden de clasificacion.
     */
    @Override
    public List<UsuarioClasificacionDTO> verUsuariosPorOrdenDeClasificacion() {
        List<Usuario> usuarios = repositorioUsuario.findAllByActivoTrueOrderByClasificacionDesc();
        return ordenarUsuario(usuarios);
    }
    /**
     * Reseta la puntuación de todos los participantes haciendo que su puntuacion sea 0.
     */
    @Override
    public void resetearPuntuacion() {
        List<Usuario> usuarios = repositorioUsuario.findAll();
        usuarios.forEach(u->u.setClasificacion((short)0));
        repositorioUsuario.saveAll(usuarios);
    }

    /**
     * Permite a un usuario apuntarse a una clase.
     * @param idUsuario es el id del usuario que se quiere apuntar a la clase.
     * @param idClase es el id de la clase a la que se va a apuntar.
     * @return la clase a la que se ha apuntado el usuario.
     * @throws ResourceNotFoundException si el usuario o la clase no existe.
     */
    @Override
    public Clase apuntarseClase(long idUsuario, long idClase) {
        Usuario usuario = comprobarUsuarioExiste(idUsuario);
        Clase clase = repositorioClase.findById(idClase).orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));
        usuario.setClase(clase);
        repositorioUsuario.save(usuario);
        return clase;
    }

    /**
     * Cambia a un usuario a activo.
     *
     * @param id el id del usuario.
     * @return el usuario reactivado.
     * @throws IllegalArgumentException  si el usuario ya esta activo.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    @Override
    public Usuario reactivarUsuario(long id) {
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no existe"));
        if (usuario.isActivo()){throw new IllegalArgumentException("El usuario ya esta activo");}
        usuario.setActivo(true);
        return repositorioUsuario.save(usuario);
    }

    /**
     * Cambia el rol del usuario.
     *
     * @param id         el id del usuario.
     * @param rolUsuario el json con el rol del usuario.
     * @return el usuario con su nuevo rol.
     * @throws IllegalArgumentException  si hay errores en la validación.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    @Override
    public Usuario cambiarRol(long id, RolUsuario rolUsuario) {
        Usuario usuario = comprobarUsuarioExiste(id);
        usuario.setRol(Rol.valueOf(rolUsuario.getRol()));
        return repositorioUsuario.save(usuario);
    }

    /**
     * Cambia la contraseña de un alumno.
     *
     * @param id                el id del usuario.
     * @param contraseñaUsuario el json con la contrasena del usuario.
     * @return el usuario reactivado.
     * @throws IllegalArgumentException  si hay errores en la validación.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    @Override
    public void cambiarContrasena(long id, ContraseñaUsuario contraseñaUsuario) {
        Usuario usuario = comprobarUsuarioExiste(id);
        usuario.setContraseña(contraseñaUsuario.getContraseña()); //cifrarla
        repositorioUsuario.save(usuario);

    }

    /**
     * crea un usuario con la informacion que tiene.
     * @param crearUsuarioDTO el DTO con la informacion para crear el usuario.
     * @param club el club al que pertenecen.
     * @return el usuario creado.
     */
    private Usuario obtenerUsuario(CrearUsuarioDTO crearUsuarioDTO, Club club){
        Usuario usuario = new Usuario();
        usuario.setNombre(crearUsuarioDTO.getNombre());
        usuario.setApellidos(crearUsuarioDTO.getApellidos());
        usuario.setFechaNacimiento(LocalDate.parse(crearUsuarioDTO.getFechaNacimiento()));
        usuario.setEmail(crearUsuarioDTO.getEmail());
        usuario.setContraseña(crearUsuarioDTO.getContraseña()); //  cifrar la contraseña antes de guardarla
        usuario.setGenero(Genero.valueOf(crearUsuarioDTO.getGenero()));
        usuario.setTelefono(crearUsuarioDTO.getTelefono());
        usuario.setRol(Rol.valueOf(crearUsuarioDTO.getRol()));
        usuario.setClub(club);
        return usuario;
    }

    /**
     * comprueba si el usuario existe y si no salta una excepcion.
     * @param id el id del usuario a comprobar.
     * @return el usuario si existe.
     * @throws ResourceNotFoundException si el usuario no existe.
     */
    private Usuario comprobarUsuarioExiste(long id){
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no existe"));
        if (!usuario.isActivo()){throw new ResourceNotFoundException("Usuario no existe");}
        return usuario;
    }

    /**
     * Funcion para crear la coleccion de DTO que marcan la posicion de cada uno en la clasificacion.
     * @param usuarios lista de los usuarios ya ordenados por orden descendente
     * @return la lista de DTO en la que se encuentran los usuarios ordenados por por clasificacion con su numero de posicion.
     */
    private List<UsuarioClasificacionDTO> ordenarUsuario(List<Usuario> usuarios){
        List<UsuarioClasificacionDTO> usuarioClasificacionDTO = new ArrayList<>();
        short clasificacion = 1;
        short numeroUsuariosEnPosicion = 0;
        boolean primerTercero = false;
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            UsuarioClasificacionDTO usuarioClasificacion = new UsuarioClasificacionDTO();
            usuarioClasificacion.setPosicion(clasificacion);
            usuarioClasificacion.setNombre(usuario.getNombre());
            usuarioClasificacion.setApellidos(usuario.getApellidos());
            usuarioClasificacion.setClasificacion(usuario.getClasificacion());
            usuarioClasificacionDTO.addLast(usuarioClasificacion);
            numeroUsuariosEnPosicion++;
            if (i < usuarios.size() - 1) {
                Usuario siguienteUsuario = usuarios.get(i + 1);

                if (siguienteUsuario.getClasificacion() != usuario.getClasificacion()) {
                    if (clasificacion == 3 && primerTercero) {
                        numeroUsuariosEnPosicion--;
                        primerTercero = false;
                        continue;
                    }
                    clasificacion += numeroUsuariosEnPosicion;
                    if (clasificacion == 3) {
                        primerTercero = true;
                        continue;
                    }
                    numeroUsuariosEnPosicion = 0;
                }
            }
        }
        return usuarioClasificacionDTO;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param email the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repositorioUsuario.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getContraseña(),
                new ArrayList<>()
        );
    }
}
