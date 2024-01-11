package com.gestion.encuesta.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String url;
    private String descripcion;
    private String fechaHora;
    private String ubicacion;
    
}
