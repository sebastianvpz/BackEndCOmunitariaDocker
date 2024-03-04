package com.gestion.encuesta.controller;

import com.gestion.encuesta.model.QuejaProblema;
import com.gestion.encuesta.model.ReporteQueja;
import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.service.QuejaService;
import com.gestion.encuesta.service.ReporteQuejaService;
import com.gestion.encuesta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes-queja")
@CrossOrigin("*")
public class ReporteQuejaController {

    @Autowired
    private ReporteQuejaService reporteQuejaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private QuejaService quejaProblemaService;

    @GetMapping("/listar")
    public List<ReporteQueja> listarReportes() {
        return reporteQuejaService.obtenerTodosLosReportes();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarReportePorId(@PathVariable Long id) {
        ReporteQueja reporte = reporteQuejaService.obtenerReportePorId(id);

        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearReporte(@RequestBody ReporteQueja reporte) {
        if (reporte.getMensaje() == null || reporte.getMensaje().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El mensaje del reporte no puede estar en blanco");
        }

        if(reporte.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if(reporte.getQuejaProblema()== null) {
            return ResponseEntity.badRequest().body("La queja no puede ser nulo");
        }
        reporteQuejaService.guardarReporte(reporte);
        return ResponseEntity.ok().body("{\"message\": \"Reporte creado exitosamente\"}");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarReporte(@PathVariable Long id, @RequestBody ReporteQueja reporte) {
        ReporteQueja reporteExistente = reporteQuejaService.obtenerReportePorId(id);

        if (reporteExistente == null) {
            return ResponseEntity.notFound().build();
        }

        if (reporte.getMensaje() == null || reporte.getMensaje().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El mensaje del reporte no puede estar en blanco");
        }

        if (reporte.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if (reporte.getQuejaProblema() == null) {
            return ResponseEntity.badRequest().body("La queja no puede ser nula");
        }

        reporteExistente.setMensaje(reporte.getMensaje());
        reporteExistente.setUsuario(reporte.getUsuario());
        reporteExistente.setQuejaProblema(reporte.getQuejaProblema());
        reporteQuejaService.guardarReporte(reporteExistente);

        return ResponseEntity.ok().body("{\"message\": \"Reporte editado exitosamente\"}");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarReporte(@PathVariable Long id) {
        try {
            reporteQuejaService.eliminarReportePorId(id);
            return ResponseEntity.ok().body("{\"message\": \"Reporte eliminado exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el reporte: " + e.getMessage());
        }
    }

    @GetMapping("/listar/usuario/{usuarioId}")
    public ResponseEntity<?> listarReportesPorUsuario(@PathVariable Long usuarioId) {
        List<ReporteQueja> reportes = reporteQuejaService.obtenerReportesPorIdDeUsuario(usuarioId);
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/listar/queja/{quejaId}")
    public ResponseEntity<?> listarReportesPorQueja(@PathVariable Long quejaId) {
        List<ReporteQueja> reportes = reporteQuejaService.obtenerReportesPorIdDeQueja(quejaId);
            return ResponseEntity.ok(reportes);

    }
}