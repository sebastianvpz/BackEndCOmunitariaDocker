package com.gestion.encuesta.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class ReporteEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaReporte;

    private String mensaje;

    @Enumerated(EnumType.STRING)
    private TipoReporte tipoReporte;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
}
