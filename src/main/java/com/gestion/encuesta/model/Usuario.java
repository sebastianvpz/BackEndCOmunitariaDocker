package com.gestion.encuesta.model;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Usuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private String password;
    
    @JsonIgnoreProperties("usuario")
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Collection<Propuesta> itemsPropuesta = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Collection<VotosPropuesta> votosPropuestas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Collection<ParticipacionProyecto> participacionProyectos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Collection<ParticipacionEvento> participacionEventos = new ArrayList<>();
   
    @JsonIgnore	
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Collection<QuejaProblema> quejasProblemas = new ArrayList<>();

}

