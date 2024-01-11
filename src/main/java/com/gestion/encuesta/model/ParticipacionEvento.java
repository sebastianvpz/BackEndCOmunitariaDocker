package com.gestion.encuesta.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ParticipacionEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String rol;
}
