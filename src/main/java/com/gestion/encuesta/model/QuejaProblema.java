package com.gestion.encuesta.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class QuejaProblema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String url;
    private String descripcion;
    private String fechaReporte;
    private String estado;  
}
