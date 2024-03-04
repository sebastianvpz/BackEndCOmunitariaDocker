package com.gestion.encuesta.service;

import com.gestion.encuesta.model.QuejaProblema;
import com.gestion.encuesta.model.ReporteQueja;
import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.repository.ReporteQuejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteQuejaService {
    @Autowired
    private ReporteQuejaRepository reporteQuejaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private QuejaService quejaProblemaService;

    public ReporteQueja obtenerReportePorUsuarioYQueja(Usuario usuario, QuejaProblema quejaProblema) {
        return reporteQuejaRepository.findByUsuarioAndQuejaProblema(usuario, quejaProblema);
    }

    public List<ReporteQueja> obtenerReportesPorIdDeQueja(Long quejaId) {
        return reporteQuejaRepository.findByQuejaProblemaId(quejaId);
    }

    public List<ReporteQueja> obtenerReportesPorIdDeUsuario(Long usuarioId) {
        return reporteQuejaRepository.findByUsuarioId(usuarioId);
    }

    public void guardarReporte(ReporteQueja reporteQueja) {
        reporteQuejaRepository.save(reporteQueja);
    }


    public List<ReporteQueja> obtenerTodosLosReportes() {
        return reporteQuejaRepository.findAll();
    }

    public ReporteQueja obtenerReportePorId(Long id) {
        Optional<ReporteQueja> reporteOptional = reporteQuejaRepository.findById(id);
        return reporteOptional.orElse(null);
    }

    public void eliminarReportePorId(Long id) {
        reporteQuejaRepository.deleteById(id);
    }

}

