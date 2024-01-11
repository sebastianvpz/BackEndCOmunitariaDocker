package com.gestion.encuesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.encuesta.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{

}
