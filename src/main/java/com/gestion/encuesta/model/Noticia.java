package com.gestion.encuesta.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String fechaHora;

    @Column(columnDefinition = "TEXT")
    private String img;

}
