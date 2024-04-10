package com.gestion.encuesta.service;

import com.gestion.encuesta.model.ReporteEvento;
import com.gestion.encuesta.repository.ReporteEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteEventoService {

    private final ReporteEventoRepository reporteEventoRepository;

    @Autowired
    public ReporteEventoService(ReporteEventoRepository reporteEventoRepository) {
        this.reporteEventoRepository = reporteEventoRepository;
    }

    // Método para guardar un reporte de evento
    public ReporteEvento guardarReporteEvento(ReporteEvento reporteEvento) {
        return reporteEventoRepository.save(reporteEvento);
    }

    // Método para obtener todos los reportes de eventos
    public List<ReporteEvento> obtenerTodosLosReportesEventos() {
        return reporteEventoRepository.findAll();
    }

    public List<ReporteEvento> obtenerReportesPorUsuario(Long usuarioid) {
        return reporteEventoRepository.findByUsuarioId(usuarioid);
    }

    public void eliminarReportePorId(Long id){
        reporteEventoRepository.deleteById(id);
    }

}
