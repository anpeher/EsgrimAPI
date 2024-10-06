package com.example.esgrimAPI.ControladorTest;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.esgrimAPI.Controlador.controladorInventario;
import com.example.esgrimAPI.Modelo.Inventario.Inventario;
import com.example.esgrimAPI.Servicio.ServicioInventario.ServicioInventarioApl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc

public class ControladorInventarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ServicioInventarioApl servicioInventarioApl;

    @InjectMocks
    private controladorInventario inventarioController;

    @BeforeEach
    public void setup() {
        // Setup si es necesario
    }

    // Prueba para obtenerItem
    @Test
    public void testObtenerItem() throws Exception {
        Inventario item = new Inventario();
        item.setId(1L);
        item.setNombreObjeto("Item1");

        when(servicioInventarioApl.verItem(1L)).thenReturn(item);

        mockMvc.perform(get("/api/inventario/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


        verify(servicioInventarioApl, times(1)).verItem(1L);
    }

    // Prueba para obtenerItems
    @Test
    public void testObtenerItems() throws Exception {
        Inventario item = new Inventario(); // Crear tu objeto Inventario
        item.setId(1L);
        item.setNombreObjeto("Item1");

        List<Inventario> itemList = Collections.singletonList(item);
        when(servicioInventarioApl.items()).thenReturn(itemList);

        mockMvc.perform(get("/api/inventario/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Item1"));

        verify(servicioInventarioApl, times(1)).items();
    }

    // Prueba para IncrementarItem
    @Test
    public void testIncrementarItem() throws Exception {
        Inventario item = new Inventario(); // Crear tu objeto Inventario
        item.setId(1L);
        item.setCantidad((short) 11); // Suponiendo que se incrementa la cantidad en 1

        when(servicioInventarioApl.insertarUno(1L)).thenReturn(item);

        mockMvc.perform(get("/api/inventario/incrementar/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.cantidad").value(11));

        verify(servicioInventarioApl, times(1)).insertarUno(1L);
    }
}
