package com.gestion.encuesta.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class VotosPropuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "propuesta_id")
    private Propuesta propuesta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private int voto;
}