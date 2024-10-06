package com.example.esgrimAPI.Modelo.Mensaje;
import com.example.esgrimAPI.Modelo.Chat.Chat;
import com.example.esgrimAPI.Modelo.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



/**
 * Entidad que representa los mensajes de un chat entre dos integrantes de un club.
 */
@Entity
@Table(name = "mensaje")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mensaje {
    /**
     * Identificador único del mensaje.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Contenido del mensaje.
     */
    @Column(name = "contenido", nullable = false, columnDefinition = "TEXT")
    private String contenido;

    /**
     * Fecha de publicación del mensaje.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion = LocalDate.now();

    /**
     * Chat al que pertenece el mensaje.
     */
    @ManyToOne()
    @JoinColumn(name = "id_chat", nullable = false)
    @JsonIgnoreProperties({"titulo", "fechaCreacion", "usuario"})
    private Chat chat;

    /**
     * ServicioUsuario que envió el mensaje.
     */
    @ManyToOne()
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"nombre", "apellidos", "email", "telefono", "rol", "club", "genero", "clase","edad","clasificacion"})
    private Usuario usuario;

}
