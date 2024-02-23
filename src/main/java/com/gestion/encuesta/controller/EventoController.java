package com.gestion.encuesta.controller;

import java.util.List;

import com.gestion.encuesta.model.Proyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.encuesta.model.Evento;
import com.gestion.encuesta.service.EventoService;

@RestController
@RequestMapping("/eventos")
@CrossOrigin("*")
public class EventoController {
	@Autowired
    private EventoService eventoService;
    //comentario de prueba
    @GetMapping("/listar")
    public List<Evento> listarEventos() {
        return eventoService.obtenerTodosLosEventos();
    }
    
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarEventoPorId(@PathVariable Long id) {
        Evento evento = eventoService.obtenerEventoPorId(id);

        if (evento != null) {
            return ResponseEntity.ok(evento);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEvento(@RequestBody Evento evento) {

        if (evento.getTitulo() == null || evento.getTitulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del evento no puede estar en blanco");
        }

        if (evento.getFechaHora() == null) {
            return ResponseEntity.badRequest().body("La fecha y hora del evento no pueden ser nulas");
        }

        if (evento.getDescripcion() == null || evento.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La descripción del evento no puede estar en blanco");
        }

        if (evento.getUbicacion() == null || evento.getUbicacion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La ubicación del evento no puede estar en blanco");
        }
        if (evento != null) {
            eventoService.guardarEvento(evento);
            return ResponseEntity.ok().body("{\"message\": \"Evento guardado exitosamente\"}");
        }else {
            return ResponseEntity.badRequest().build();
        }


           }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEvento(@PathVariable Long id, @RequestBody Evento evento) {

        if (evento.getTitulo() == null || evento.getTitulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del evento no puede estar en blanco");
        }

        if (evento.getFechaHora() == null) {
            return ResponseEntity.badRequest().body("La fecha y hora del evento no pueden ser nulas");
        }

        if (evento.getDescripcion() == null || evento.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La descripción del evento no puede estar en blanco");
        }

        if (evento.getUbicacion() == null || evento.getUbicacion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La ubicación del evento no puede estar en blanco");
        }

        Evento eventoExistente = eventoService.obtenerEventoPorId(id);
        if (eventoExistente == null) {
            return ResponseEntity.badRequest().body("El evento a editar no existe");
        }

        eventoExistente.setTitulo(evento.getTitulo());
        eventoExistente.setUrl(evento.getUrl());
        eventoExistente.setDescripcion(evento.getDescripcion());
        eventoExistente.setFechaHora(evento.getFechaHora());
        eventoExistente.setUbicacion(evento.getUbicacion());
        evento.setId(id);
        eventoService.guardarEvento(eventoExistente);
        return ResponseEntity.ok().body("{\"message\": \"Evento Editado exitosamente \"}");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEvento(@PathVariable Long id) {
        try {
            eventoService.eliminarEventoPorId(id);
            return ResponseEntity.ok().body("{\"message\": \"Evento Eliminado exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el evento: " + e.getMessage());
        }
    }

}
