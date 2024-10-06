package com.example.esgrimAPI.Repositorio;


import com.example.esgrimAPI.Modelo.Chat.Chat;
import com.example.esgrimAPI.Modelo.Usuario.Rol;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio donde se gestionan las operaciones CRUD de la entidad {@link Usuario}
 */
@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    /**
     * Un findAll pero solo teniendo en cuenta los usuarios en activo
     * @return una lista de usuarios activos.
     */
    List<Usuario> findAllByActivoTrue();

    /**
     * Busqueda de los usuarios activos devolviéndolos en orden de su clasificacion.
     * @return una lista de usuarios ordenados por su clasificacion.
     */
    List<Usuario> findAllByActivoTrueOrderByClasificacionDesc();

    /**
     * Selecciona los usuarios que sean de una clase especifica.
     * @param id el id de la clase en la que buscamos los usuarios.
     * @return la lista de usuarios.
     */
    @Query("SELECT u FROM Usuario u WHERE u.clase.id = :id AND u.activo = true")
    List<Usuario> findUsuariosClaseActivos(Long id);

    /**
     * Búsqueda para comprobar que existe un usuario con algun telelefono determinado.
     * @param telefono el telefono que queremos comprobar.
     * @return true si existe alguno con ese mismo numero de telefono y false en caso contrario.
     */
    boolean existsByTelefono(String telefono);

    /**
     * Búsqueda para comprobar que existe un usuario con algun email determinado.
     * @param email el email que queremos comprobar.
     * @return true si existe alguno con ese mismo email y false en caso contrario.
     */
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    /**
     * Búsqueda para obtener los usuarios con un rol en especifico.
     * @param rol el rol que se busca.
     * @return lista de usuarios con ese rol asignado.
     */
    List<Usuario> findByRolAndActivoTrue(Rol rol);
}
