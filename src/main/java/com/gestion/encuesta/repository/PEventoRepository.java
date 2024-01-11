package com.gestion.encuesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.encuesta.model.ParticipacionEvento;

public interface PEventoRepository extends JpaRepository<ParticipacionEvento, Long>{

}
