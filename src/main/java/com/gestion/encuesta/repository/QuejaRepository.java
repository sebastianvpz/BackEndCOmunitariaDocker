package com.gestion.encuesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.encuesta.model.QuejaProblema;

public interface QuejaRepository extends JpaRepository<QuejaProblema, Long>{

}
