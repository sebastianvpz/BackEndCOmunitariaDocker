package com.gestion.encuesta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.encuesta.model.Evento;
import com.gestion.encuesta.model.ParticipacionEvento;
import com.gestion.encuesta.model.Usuario;

public interface PEventoRepository extends JpaRepository<ParticipacionEvento, Long>{
	ParticipacionEvento findByUsuarioAndEvento(Usuario usuario, Evento evento);
	List<ParticipacionEvento> findByEventoId(Long eventoId);
}
