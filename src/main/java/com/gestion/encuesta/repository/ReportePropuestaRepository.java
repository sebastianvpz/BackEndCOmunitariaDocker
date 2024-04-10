package com.gestion.encuesta.repository;

import com.gestion.encuesta.model.ReportePropuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportePropuestaRepository extends JpaRepository<ReportePropuesta, Long> {

    List<ReportePropuesta> findByUsuarioId(Long usuarioId);
}
