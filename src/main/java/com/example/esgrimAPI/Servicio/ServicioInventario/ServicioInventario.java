package com.example.esgrimAPI.Servicio.ServicioInventario;


import com.example.esgrimAPI.DTO.Inventario.CrearItemDTO;
import com.example.esgrimAPI.DTO.Inventario.ModItemDTO;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Modelo.Inventario.Inventario;


import java.util.List;

/**
 * Interfaz del servicio de inventario donde se definen todas sus operaciones.
 */
public interface ServicioInventario {

    /**
     * Crea un nuevo objeto en el inventario.
     * @param crearItemDTO el json con la informacion para crear un nuevo objeto para el invetario.
     * @return el nuevo item del inventario creado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     */
    Inventario crearItem(CrearItemDTO crearItemDTO);

    /**
     * Modifica la informacion de un item del inventario.
     *
     * @param modItemDTO el json con la informacion para modificar un item.
     * @param id el json con la informacion para modificar un item.
     * @return el nuevo item modificado.
     * @throws IllegalArgumentException  si ha hay algún problema en la validación.
     * @throws ResourceNotFoundException si el item no existe.
     */
    Inventario modItem(Long id, ModItemDTO modItemDTO);

    /**
     * Devuelve un item para ver su informacion.
     *
     * @param id el id del inventario
     * @return la informacion del Item
     * @throws ResourceNotFoundException si el item no existe.
     */
    Inventario verItem(long id);

    /**
     * Pone a 0 las existencias de un item especifico.
     *
     * @param id el id del Item
     * @return la informacion del Item
     * @throws ResourceNotFoundException si el Item no existe.
     */
    void eliminarItem(long id);

    /**
     * Devuelve una lista de todos los items del inventario.
     * @return lista de items que contiene el inventario.
     */
    List<Inventario> items();

    /**
     * Inserta 1 al item que selecciones.
     *
     * @param id el id del inventario
     * @return la informacion del Item
     * @throws ResourceNotFoundException si el item no existe.
     */
    Inventario insertarUno(long id);

    /**
     * Extrae 1 al item que selecciones.
     *
     * @param id el id del inventario
     * @return la informacion del Item
     * @throws ResourceNotFoundException si el item no existe.
     */
    Inventario extraerUno(long id);;

}
