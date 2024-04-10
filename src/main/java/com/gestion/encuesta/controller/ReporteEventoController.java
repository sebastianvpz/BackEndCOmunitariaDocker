package com.gestion.encuesta.controller;

import com.gestion.encuesta.dto.ReporteEventoDTO;
import com.gestion.encuesta.model.Evento;
import com.gestion.encuesta.model.ReporteEvento;
import com.gestion.encuesta.model.TipoReporte;
import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.service.EventoService;
import com.gestion.encuesta.service.ReporteEventoService;
import com.gestion.encuesta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes-eventos")
@CrossOrigin("*")
public class ReporteEventoController {

    private final ReporteEventoService reporteEventoService;
    private final UsuarioService usuarioService;
    private final EventoService eventoService;

    public ReporteEventoController(ReporteEventoService reporteEventoService, UsuarioService usuarioService, EventoService eventoService) {
        this.reporteEventoService = reporteEventoService;
        this.usuarioService = usuarioService;
        this.eventoService = eventoService;
    }


    @PostMapping("/crear")
    public ResponseEntity<ReporteEvento> crearReporteEvento(@RequestBody ReporteEventoDTO reporteEventoDTO) {
        // Aquí deberías obtener los objetos Usuario y Evento basados en los IDs recibidos
        Usuario usuario = usuarioService.obtenerUsuarioPorId(reporteEventoDTO.getUsuarioId());
        Evento evento = eventoService.obtenerEventoPorId(reporteEventoDTO.getEventoId());

        // Crear un nuevo objeto ReporteEvento usando los objetos Usuario y Evento
        ReporteEvento reporteEvento = new ReporteEvento();
        reporteEvento.setFechaReporte(reporteEventoDTO.getFechaReporte());
        reporteEvento.setMensaje(reporteEventoDTO.getMensaje());
        reporteEvento.setTipoReporte(TipoReporte.EVENTO);
        reporteEvento.setUsuario(usuario);
        reporteEvento.setEvento(evento);

        // Guardar el nuevo reporte de evento
        ReporteEvento nuevoReporteEvento = reporteEventoService.guardarReporteEvento(reporteEvento);
        return new ResponseEntity<>(nuevoReporteEvento, HttpStatus.CREATED);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<ReporteEvento>> listarReportesEventos() {
        List<ReporteEvento> reportesEventos = reporteEventoService.obtenerTodosLosReportesEventos();
        return new ResponseEntity<>(reportesEventos, HttpStatus.OK);
    }

}
