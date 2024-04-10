package com.gestion.encuesta.controller;

import com.gestion.encuesta.dto.ReporteGeneral;
import com.gestion.encuesta.model.ReporteEvento;
import com.gestion.encuesta.model.ReportePropuesta;
import com.gestion.encuesta.model.ReporteQueja;
import com.gestion.encuesta.service.ReporteEventoService;
import com.gestion.encuesta.service.ReportePropuestaService;
import com.gestion.encuesta.service.ReporteQuejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteQuejaService reporteQuejaService;

    @Autowired
    private ReportePropuestaService reportePropuestaService;

    @Autowired
    private ReporteEventoService reporteEventoService;

    @GetMapping("/usuario/{usuarioId}")
    public List<ReporteGeneral> obtenerReportesPorUsuario(@PathVariable Long usuarioId) {
        List<ReporteGeneral> reportesGenerales = new ArrayList<>();

        // Obtener reportes de queja
        List<ReporteQueja> reportesQueja = reporteQuejaService.obtenerReportesPorIdDeUsuario(usuarioId);
        for (ReporteQueja reporteQueja : reportesQueja) {
            ReporteGeneral reporteGeneral = new ReporteGeneral();
            reporteGeneral.setId(reporteQueja.getId());
            reporteGeneral.setFechaReporte(reporteQueja.getFechaReporte());
            reporteGeneral.setMensaje(reporteQueja.getMensaje());
            reporteGeneral.setTipoReporte("QUEJA");
            reportesGenerales.add(reporteGeneral);
        }

        // Obtener reportes de propuesta
        List<ReportePropuesta> reportesPropuesta = reportePropuestaService.obtenerReportesPorUsuario(usuarioId);
        for (ReportePropuesta reportePropuesta : reportesPropuesta) {
            ReporteGeneral reporteGeneral = new ReporteGeneral();
            reporteGeneral.setId(reportePropuesta.getId());
            reporteGeneral.setFechaReporte(reportePropuesta.getFechaReporte());
            reporteGeneral.setMensaje(reportePropuesta.getMensaje());
            reporteGeneral.setTipoReporte("PROPUESTA");
            reportesGenerales.add(reporteGeneral);
        }

        // Obtener reportes de evento
        List<ReporteEvento> reportesEvento = reporteEventoService.obtenerReportesPorUsuario(usuarioId);
        for (ReporteEvento reporteEvento : reportesEvento) {
            ReporteGeneral reporteGeneral = new ReporteGeneral();
            reporteGeneral.setId(reporteEvento.getId());
            reporteGeneral.setFechaReporte(reporteEvento.getFechaReporte());
            reporteGeneral.setMensaje(reporteEvento.getMensaje());
            reporteGeneral.setTipoReporte("EVENTO");
            reportesGenerales.add(reporteGeneral);
        }

        return reportesGenerales;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarReporte(@PathVariable Long id, @RequestParam String tipo) {
        try {
            if (tipo.equals("queja")) {
                reporteQuejaService.eliminarReportePorId(id);
            } else if (tipo.equals("evento")) {
                reporteEventoService.eliminarReportePorId(id);
            } else if (tipo.equals("propuesta")) {
                reportePropuestaService.eliminarReportePorId(id);
            } else {
                return ResponseEntity.badRequest().body("Tipo de reporte no válido");
            }

            return ResponseEntity.ok().body("{\"message\": \"Reporte eliminado exitosamente\"}");
        } catch (Exception e) {
            System.out.println("Error al eliminar el reporte");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el reporte: " + e.getMessage());
        }
    }


}