package com.gestion.encuesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.encuesta.model.ParticipacionProyecto;

public interface PProyectoRepository extends JpaRepository<ParticipacionProyecto, Long>{

}
