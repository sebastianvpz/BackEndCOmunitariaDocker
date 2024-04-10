package com.gestion.encuesta.service;

import com.gestion.encuesta.model.ReportePropuesta;
import com.gestion.encuesta.repository.ReportePropuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportePropuestaService {

    private final ReportePropuestaRepository reportePropuestaRepository;

    @Autowired
    public ReportePropuestaService(ReportePropuestaRepository reportePropuestaRepository) {
        this.reportePropuestaRepository = reportePropuestaRepository;
    }

    // Método para agregar un nuevo reporte de propuesta
    public ReportePropuesta agregarReportePropuesta(ReportePropuesta reportePropuesta) {
        return reportePropuestaRepository.save(reportePropuesta);
    }

    // Método para listar todos los reportes de propuesta
    public List<ReportePropuesta> listarReportesPropuesta() {
        return reportePropuestaRepository.findAll();
    }

    public List<ReportePropuesta> obtenerReportesPorUsuario(Long usuarioid) {
        return  reportePropuestaRepository.findByUsuarioId(usuarioid);

    }
    public void eliminarReportePorId(Long id) {
        reportePropuestaRepository.deleteById(id);
    }
}
