package com.gestion.encuesta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Propuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(columnDefinition = "LONGTEXT")
    private String img;
    private String titulo;
    private String ubicacion;
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "propuesta", cascade = CascadeType.ALL)
    private List<VotosPropuesta> votosPropuestas;
}
