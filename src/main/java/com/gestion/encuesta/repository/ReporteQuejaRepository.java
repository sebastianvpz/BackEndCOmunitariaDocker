package com.gestion.encuesta.repository;

import com.gestion.encuesta.model.QuejaProblema;
import com.gestion.encuesta.model.ReporteQueja;
import com.gestion.encuesta.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteQuejaRepository extends JpaRepository<ReporteQueja, Long> {
    List<ReporteQueja> findByQuejaProblemaId(Long quejaId);
    ReporteQueja findByUsuarioAndQuejaProblema(Usuario usuario, QuejaProblema quejaProblema);
    List<ReporteQueja> findByUsuarioId(Long usuarioId);
}
