package com.gestion.encuesta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class QuejaProblema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("quejasProblemas")
    private Usuario usuario;
    private String titulo;
    private String ubicacion;
    private String descripcion;    
    private String fechaReporte;
    private String estado;
    @Column(columnDefinition = "TEXT")
    private String img;

    @JsonIgnore
    @OneToMany(mappedBy = "quejaProblema", cascade = CascadeType.ALL)
    private List<ReporteQueja> reporteQuejas;
}
