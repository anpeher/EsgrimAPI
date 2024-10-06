package com.example.esgrimAPI.Servicio.ServicioInventario;


import com.example.esgrimAPI.DTO.Inventario.CrearItemDTO;
import com.example.esgrimAPI.DTO.Inventario.ModItemDTO;
import com.example.esgrimAPI.Modelo.Club.Club;
import com.example.esgrimAPI.Modelo.Inventario.Inventario;
import com.example.esgrimAPI.Excepciones.ResourceNotFoundException;
import com.example.esgrimAPI.Repositorio.RepositorioClub;
import com.example.esgrimAPI.Repositorio.RepositorioInventario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ServicioInventarioApl implements ServicioInventario{
    private final long idClub = 1;
    private RepositorioInventario repositorioInventario;
    private RepositorioClub repositorioClub;
    /**
     * Crea un nuevo objeto en el inventario.
     *
     * @param crearItemDTO el json con la informacion para crear un nuevo objeto para el invetario.
     * @return el nuevo item del inventario creado.
     * @throws IllegalArgumentException si ha hay algún problema en la validación.
     */
    @Override
    public Inventario crearItem(CrearItemDTO crearItemDTO) {
        Club club = repositorioClub.findById(idClub)
                .orElseThrow(() -> new IllegalArgumentException("Club no encontrado"));
        return repositorioInventario.save(obtenerItem(crearItemDTO, club));
    }

    /**
     * Modifica la informacion de un item del inventario.
     *
     * @param id  el json con la informacion para modificar un item.
     * @param modItemDTO el json con la informacion para modificar un item.
     * @return el nuevo item modificado.
     * @throws IllegalArgumentException  si ha hay algún problema en la validación.
     * @throws ResourceNotFoundException si el item no existe.
     */
    @Override
    public Inventario modItem(Long id, ModItemDTO modItemDTO) {
        Inventario inventario = repositorioInventario.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));
        Optional.ofNullable(modItemDTO.getCantidad()).ifPresent(inventario::setCantidad);
        Optional.ofNullable(modItemDTO.getNombreObjeto()).ifPresent(inventario::setNombreObjeto);
        Optional.ofNullable(modItemDTO.getFabricante()).ifPresent(inventario::setFabricante);
        return repositorioInventario.save(inventario);

    }

    /**
     * Devuelve un item para ver su informacion.
     *
     * @param id el id del inventario
     * @return la informacion del Item
     * @throws ResourceNotFoundException si el item no existe.
     */
    @Override
    public Inventario verItem(long id) {
        return repositorioInventario.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));
    }

    /**
     * Pone a 0 las existencias de un item especifico.
     *
     * @param id el id del Item
     * @throws ResourceNotFoundException si el Item no existe.
     */
    @Override
    public void eliminarItem(long id) {
        Inventario inventario = repositorioInventario.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));
        inventario.setCantidad((short)0);
        repositorioInventario.save(inventario);
    }

    /**
     * Devuelve una lista de todos los items del inventario.
     *
     * @return lista de items que contiene el inventario.
     */
    @Override
    public List<Inventario> items() {
        return repositorioInventario.findAll();
    }

    /**
     * Inserta 1 al item que selecciones.
     *
     * @param id el id del inventario
     * @return la informacion del Item
     * @throws ResourceNotFoundException si el item no existe.
     */
    @Override
    public Inventario insertarUno(long id) {
        Inventario inventario = repositorioInventario.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));
        inventario.setCantidad((short)(inventario.getCantidad()+1));
        return repositorioInventario.save(inventario);
    }

    /**
     * Extrae 1 al item que selecciones.
     *
     * @param id el id del inventario
     * @return la informacion del Item
     * @throws ResourceNotFoundException si el item no existe.
     */
    @Override
    public Inventario extraerUno(long id) {
        Inventario inventario = repositorioInventario.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));
        inventario.setCantidad((short)(inventario.getCantidad()-1));
        return repositorioInventario.save(inventario);
    }

    /**
     * Crea el item usando el json recibido y el club, el cual ya se han comprobado que existe.
     * @param crearItemDTO json que contiene la informacion para crear un item.
     * @param club club al que pertenece.
     * @return el item creado si tod0 ha salido bien.
     */
    private Inventario obtenerItem(CrearItemDTO crearItemDTO, Club club){
        Inventario item = new Inventario();
        item.setCantidad(crearItemDTO.getCantidad());
        item.setNombreObjeto(crearItemDTO.getNombreObjeto());
        item.setFabricante(crearItemDTO.getFabricante());
        item.setClub(club);
        return item;
    }
}
