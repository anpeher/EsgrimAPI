package com.example.esgrimAPI.Servicio.ServicioClase;

import com.example.esgrimAPI.DTO.Clase.CrearClaseDTO;
import com.example.esgrimAPI.DTO.Clase.ModClaseDTO;
import com.example.esgrimAPI.Modelo.Clase.Clase;
import com.example.esgrimAPI.Modelo.Clase.DiaSemana;
import com.example.esgrimAPI.Modelo.Clase.Modalidad;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.example.esgrimAPI.Repositorio.RepositorioClase;
import com.example.esgrimAPI.Repositorio.RepositorioUsuario;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioClaseApl implements ServicioClase{

    private RepositorioClase repositorioClase;
    private RepositorioUsuario repositorioUsuario;
    /**
     * Crea una nueva clase para poder enviarse mensajes.
     *
     * @param crearClaseDTO el json con la informacion para crear una clase
     * @return la nueva clase creada.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     */
    @Override
    public Clase crearClase(CrearClaseDTO crearClaseDTO) {
        Usuario usuario = repositorioUsuario.findById(crearClaseDTO.getMaestro())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        if (!usuario.isActivo()){throw new IllegalArgumentException("Usuario no encontrado");}
        if(ComprobarHorario(crearClaseDTO.getHorarioInicio(), crearClaseDTO.getHorarioFin())) {throw new IllegalArgumentException("la hora de inicio debe ser anterior a la hora de fin.");}
        return repositorioClase.save(obtenerClase(crearClaseDTO, usuario));
    }

    /**
     * Modifica la informacion de una clase.
     *
     * @param id el id de la clase para modificar.
     * @param modClaseDTO el json con la informacion para modificar una clase
     * @return la nueva clase modificada.
     * @throws IllegalArgumentException  si ha hay algún problema en la validación.
     * @throws ResourceNotFoundException si la clase no existe.
     */
    @Override
    public Clase modClase(Long id, ModClaseDTO modClaseDTO) {
        Clase clase = repositorioClase.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));
        if (modClaseDTO.getMaestro() != null){
            Usuario usuario = repositorioUsuario.findById(modClaseDTO.getMaestro())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            if (!usuario.isActivo()){throw new IllegalArgumentException("Usuario no encontrado");}
            clase.setMaestro(usuario);
        }
        if(modClaseDTO.getHorarioInicio()!= null && modClaseDTO.getHorarioFin()!= null){
            if(ComprobarHorario(modClaseDTO.getHorarioInicio(), modClaseDTO.getHorarioFin())) {throw new IllegalArgumentException("la hora de inicio debe ser anterior a la hora de fin.");}
            clase.setHorarioInicio(LocalTime.parse(modClaseDTO.getHorarioInicio()));
            clase.setHorarioFin(LocalTime.parse(modClaseDTO.getHorarioFin()));
        } else if (modClaseDTO.getHorarioInicio()!= null){
            if(ComprobarHorario(modClaseDTO.getHorarioInicio(), clase.getHorarioFin().toString())) {throw new IllegalArgumentException("la hora de inicio debe ser anterior a la hora de fin.");}
            clase.setHorarioInicio(LocalTime.parse(modClaseDTO.getHorarioInicio()));
        } else if (modClaseDTO.getHorarioFin()!= null){
            if(ComprobarHorario(clase.getHorarioInicio().toString(), modClaseDTO.getHorarioFin())) {throw new IllegalArgumentException("la hora de inicio debe ser anterior a la hora de fin.");}
            clase.setHorarioFin(LocalTime.parse(modClaseDTO.getHorarioFin()));
        }
        Optional.ofNullable(modClaseDTO.getModalidad())
                .map(Modalidad::valueOf)
                .ifPresent(clase::setModalidad);
        Optional.ofNullable(modClaseDTO.getDias())
                .map(this::StringAEnum)
                .ifPresent(clase::setDias);

        return repositorioClase.save(clase);
    }

    /**
     * Devuelve una clase para ver su informacion.
     *
     * @param id el id de la clase
     * @return la informacion del clase
     * @throws ResourceNotFoundException si la clase no existe.
     */
    @Override
    public Clase verClase(long id) {
        Clase clase = repositorioClase.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));
        return clase;
    }

    /**
     * Elimina una clase.
     *
     * @param id el id de la clase
     * @return la informacion del clase
     * @throws ResourceNotFoundException si la clase no existe.
     */
    @Override
    public void eliminarClase(long id) {
        Clase clase = repositorioClase.findById(id).orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));
        repositorioClase.delete(clase);
    }

    /**
     * Muesta los usuarios inscritos en una clase.
     *
     * @return una lista de usuarios inscritos a una clase.
     */
    @Override
    public List<Usuario> UsuariosInscritos(long id) {
        return repositorioUsuario.findUsuariosClaseActivos(id);
    }

    /**
     * Crea la clase usando el json recibido y con el ususario, el cual ya se han comprobado que existe.
     * @param crearClaseDTO json que contiene la informacion para crear una clase.
     * @param usuario usuario que crea el mensaje.
     * @return la clase creada si tod0 ha salido bien.
     */
    private Clase obtenerClase(CrearClaseDTO crearClaseDTO, Usuario usuario){

        Clase clase = new Clase();
        clase.setModalidad(Modalidad.valueOf(crearClaseDTO.getModalidad()));
        clase.setDias(StringAEnum(crearClaseDTO.getDias()));
        clase.setHorarioInicio(LocalTime.parse(crearClaseDTO.getHorarioInicio()));
        clase.setHorarioFin(LocalTime.parse(crearClaseDTO.getHorarioFin()));
        clase.setMaestro(usuario);
        return clase;
    }

    /**
     * Transforma una lista de string a un array del enum DiasSemana.
     * @param diasSemanaStrings lista de string con valores del enum
     * @return array de enum DiasSemana
     */
    private DiaSemana[] StringAEnum(List<String> diasSemanaStrings) {
        return diasSemanaStrings.stream()
                .map(DiaSemana::valueOf)
                .toArray(DiaSemana[]::new);
    }

    /**
     * comprobacion para que la hora de inicio nunca sea más tarde que la hora final
     * @param horaInicio la hora de inicio.
     * @param horaFin la hora de fin.
     * @return true si la hora de inicio es anterior a la hora de in y false en caso contrario.
     */
    private boolean ComprobarHorario(String horaInicio, String horaFin){
        return !LocalTime.parse(horaInicio).isBefore(LocalTime.parse(horaFin));
    }
}
