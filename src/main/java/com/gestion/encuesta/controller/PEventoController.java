package com.gestion.encuesta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.gestion.encuesta.model.ParticipacionEvento;
import com.gestion.encuesta.service.PEventoService;

@RestController
@RequestMapping("/peventos")
@CrossOrigin("*")
public class PEventoController {
    @Autowired
    private PEventoService pEventoService;

    @GetMapping("/listar")
    public List<ParticipacionEvento> listarPEventos() {
        return pEventoService.obtenerTodosLosPEventos();
    }
    
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPEventoPorId(@PathVariable Long id) {
        ParticipacionEvento participacionEvento = pEventoService.obtenerPEventoPorId(id);
        if (participacionEvento != null) {
            return ResponseEntity.ok(participacionEvento);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPEvento(@RequestBody ParticipacionEvento pEvento) {
        // Verificar si ya existe una participación para el usuario y el evento
        ParticipacionEvento participacionExistente = pEventoService.obtenerParticipacionPorUsuarioYEvento(pEvento.getUsuario(), pEvento.getEvento());
        if (participacionExistente != null) {
            // Devolver un mensaje indicando que el usuario ya está participando en este evento
            return ResponseEntity.badRequest().body("El usuario ya está participando en este evento");
        }

        // Si no hay participación existente, proceder con el guardado
        if (pEvento.getRol() == null || pEvento.getRol().trim().isEmpty()) {
            pEvento.setRol("Asistente");
        }

        pEventoService.guardarPEvento(pEvento);
        // Devolver un objeto JSON con un mensaje de éxito
        return ResponseEntity.ok().body("{\"message\": \"Participación en evento guardada exitosamente\"}");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPEvento(@PathVariable Long id, @RequestBody ParticipacionEvento pEvento) {
        if (pEvento.getEvento() == null) {
            return ResponseEntity.badRequest().body("El evento no puede ser nulo");
        }

        if (pEvento.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

     // Establecer el rol por defecto como 'Asistente' si no se proporciona uno
        if (pEvento.getRol() == null || pEvento.getRol().trim().isEmpty()) {
            pEvento.setRol("Asistente");
        }
        ParticipacionEvento peventoExistente = pEventoService.obtenerPEventoPorId(id);
        if (peventoExistente == null) {
            return ResponseEntity.badRequest().body("La participacion a editar no existe");
        }
        
        pEvento.setId(id);
        pEventoService.guardarPEvento(pEvento);
        return ResponseEntity.ok("Participación en evento editada exitosamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPEvento(@PathVariable Long id) {
        pEventoService.eliminarPEventoPorId(id);
    }
}