package com.gestion.encuesta.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String titulo;
    private String descripcion;
    private String estado;
}