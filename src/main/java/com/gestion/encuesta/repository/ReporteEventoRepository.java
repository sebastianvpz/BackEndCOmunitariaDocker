package com.gestion.encuesta.repository;

import com.gestion.encuesta.model.ReporteEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteEventoRepository extends JpaRepository<ReporteEvento, Long> {

    List<ReporteEvento> findByUsuarioId(Long usuarioId);
}

