package com.example.esgrimAPI.Servicio.ServicioClase;


import com.example.esgrimAPI.DTO.Clase.CrearClaseDTO;
import com.example.esgrimAPI.DTO.Clase.ModClaseDTO;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Clase.Clase;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;

import java.util.List;

/**
 * Interfaz del servicio de clase donde se definen todas sus operaciones.
 */
public interface ServicioClase {

    /**
     * Crea una nueva clase para poder enviarse mensajes.
     * @param crearClaseDTO el json con la informacion para crear una clase
     * @return la nueva clase creada.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     */
    Clase crearClase(CrearClaseDTO crearClaseDTO);

    /**
     * Modifica la informacion de una clase.
     * @param modClaseDTO el json con la informacion para modificar una clase
     * @param id el id de la clase para modificar.
     * @return la nueva clase modificada.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     * @throws ResourceNotFoundException si la clase no existe.
     */
    Clase modClase(Long id, ModClaseDTO modClaseDTO);

    /**
     * Devuelve una clase para ver su informacion.
     * @param id el id de la clase
     * @return la informacion del clase
     * @throws ResourceNotFoundException si la clase no existe.
     */
    Clase verClase(long id);

    /**
     * Elimina una clase.
     * @param id el id de la clase
     * @return la informacion del clase
     * @throws ResourceNotFoundException si la clase no existe.
     */
    void eliminarClase(long id);

    /**
     * Muesta los usuarios inscritos en una clase.
     * @return una lista de usuarios inscritos a una clase.
     */
    List<Usuario> UsuariosInscritos(long id);
}
