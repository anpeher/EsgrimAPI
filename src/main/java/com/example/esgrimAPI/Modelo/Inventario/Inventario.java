package com.example.esgrimAPI.Modelo.Inventario;
import com.example.esgrimAPI.Modelo.Club.Club;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;



/**
 * Entidad que representa el inventrio que gestiona el club.
 */
@Entity
@Table(name = "inventario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventario {
    /**
     * Identificador único del objeto de inventario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del objeto de inventario.
     */
    @Column(name = "nombre_objeto", nullable = false, length = 100)
    private String nombreObjeto;

    /**
     * Fabricante del objeto de inventario.
     */
    @Column(name = "fabricante", nullable = false, length = 100)
    private String fabricante;

    /**
     * Cantidad del objeto disponible en el inventario.
     */
    @Column(name = "cantidad", nullable = false)
    private Short cantidad;

    /**
     * Club al que pertenece el objeto de inventario.
     */
    @ManyToOne
    @JoinColumn(name = "id_club", nullable = false)
    @JsonIgnoreProperties({"nombre","direccion","telefono","email"})
    private Club club;
}
