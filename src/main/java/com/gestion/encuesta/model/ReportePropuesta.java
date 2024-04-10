package com.gestion.encuesta.model;


import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class ReportePropuesta {
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
    @JoinColumn(name = "propuesta_id")
    private Propuesta propuesta;
}

