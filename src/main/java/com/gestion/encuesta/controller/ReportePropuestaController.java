package com.gestion.encuesta.controller;

import com.gestion.encuesta.dto.ReportePropuestaDTO;
import com.gestion.encuesta.model.Propuesta;
import com.gestion.encuesta.model.ReportePropuesta;
import com.gestion.encuesta.model.TipoReporte;
import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.service.PropuestaService;
import com.gestion.encuesta.service.ReportePropuestaService;
import com.gestion.encuesta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes-propuestas")
@CrossOrigin("*")
public class ReportePropuestaController {

    private final ReportePropuestaService reportePropuestaService;
    private final UsuarioService usuarioService;
    private final PropuestaService propuestaService;


    @Autowired
    public ReportePropuestaController(ReportePropuestaService reportePropuestaService, UsuarioService usuarioService, PropuestaService propuestaService) {
        this.reportePropuestaService = reportePropuestaService;
        this.usuarioService = usuarioService;
        this.propuestaService = propuestaService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<ReportePropuesta> agregarReportePropuesta(@RequestBody ReportePropuestaDTO reportePropuestaDTO) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(reportePropuestaDTO.getUsuarioId());
        Propuesta propuesta = propuestaService.obtenerPropuestaPorId(reportePropuestaDTO.getPropuestaId());

        ReportePropuesta reportePropuesta = new ReportePropuesta();
        reportePropuesta.setFechaReporte(reportePropuestaDTO.getFechaReporte());
        reportePropuesta.setMensaje(reportePropuestaDTO.getMensaje());
        reportePropuesta.setTipoReporte(TipoReporte.PROPUESTA);
        reportePropuesta.setUsuario(usuario);
        reportePropuesta.setPropuesta(propuesta);

        ReportePropuesta nuevoReportePropuesta = reportePropuestaService.agregarReportePropuesta(reportePropuesta);

        return new ResponseEntity<>(nuevoReportePropuesta, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ReportePropuesta>> listarReportesPropuesta() {
        List<ReportePropuesta> reportesPropuesta = reportePropuestaService.listarReportesPropuesta();
        return new ResponseEntity<>(reportesPropuesta, HttpStatus.OK);
    }

}
