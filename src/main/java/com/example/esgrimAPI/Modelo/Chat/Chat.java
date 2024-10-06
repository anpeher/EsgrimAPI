package com.example.esgrimAPI.Modelo.Chat;
import com.example.esgrimAPI.Modelo.Mensaje.Mensaje;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Entidad que representa un chat entre dos usuarios.
 */
@Entity
@Table(name = "chat")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chat {

    /**
     * Identificador único del chat.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * titulo del chat.
     */
    @Column(name = "titulo ", nullable = false)
    private String titulo;

    /**
     * fecha en la que se creo el chat.
     */
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion = LocalDate.now();

    /**
     * ServicioUsuario que creo el chat
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario_creador", nullable = false)
    @JsonIgnoreProperties({"nombre", "apellidos", "email", "telefono", "rol", "club", "genero", "clase","edad","clasificacion"})
    private Usuario usuarioCreador;

    /**
     * ServicioUsuario que participa en el chat
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario_participante", nullable = false)
    @JsonIgnoreProperties({"nombre", "apellidos", "email", "telefono", "rol", "club", "genero", "clase","edad","clasificacion"})
    private Usuario usuarioParticipante;

}

