package com.gestion.encuesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.encuesta.model.Evento;
import com.gestion.encuesta.model.ParticipacionEvento;
import com.gestion.encuesta.model.Usuario;

public interface PEventoRepository extends JpaRepository<ParticipacionEvento, Long>{
	ParticipacionEvento findByUsuarioAndEvento(Usuario usuario, Evento evento);
}
