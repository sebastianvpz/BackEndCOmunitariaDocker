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

import com.gestion.encuesta.model.QuejaProblema;
import com.gestion.encuesta.service.QuejaService;

@RestController
@RequestMapping("/quejas")
@CrossOrigin("*")
public class QuejaController {
    @Autowired
    private QuejaService quejaService;

    @GetMapping("/listar")
    public List<QuejaProblema> listarQuejas() {
        return quejaService.obtenerTodasLasQuejas();
    }
    
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarQuejaPorId(@PathVariable Long id) {
        QuejaProblema quejaProblema = quejaService.obtenerQuejaPorId(id);
        if (quejaProblema != null){
            return ResponseEntity.ok(quejaProblema);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarQueja(@RequestBody QuejaProblema queja) {
        // Validaciones manuales
        if (queja.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if (queja.getUrl() == null || queja.getUrl().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La URL no puede estar en blanco");
        }

        if (queja.getDescripcion() == null || queja.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La descripción no puede estar en blanco");
        }

        if (queja.getFechaReporte() == null || queja.getFechaReporte().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La fecha de reporte no puede estar en blanco");
        }

        if (queja.getEstado() == null || queja.getEstado().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El estado no puede estar en blanco");
        }


        quejaService.guardarQueja(queja);
        return ResponseEntity.ok().body("{\"message\": \"Queja Guardada exitosamente \"}");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarQueja(@PathVariable Long id, @RequestBody QuejaProblema queja) {
        if (queja.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if (queja.getUrl() == null || queja.getUrl().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La URL no puede estar en blanco");
        }

        if (queja.getDescripcion() == null || queja.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La descripción no puede estar en blanco");
        }

        if (queja.getFechaReporte() == null || queja.getFechaReporte().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La fecha de reporte no puede estar en blanco");
        }

        if (queja.getEstado() == null || queja.getEstado().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El estado no puede estar en blanco");
        }

        QuejaProblema quejaExistente = quejaService.obtenerQuejaPorId(id);
        if (quejaExistente == null) {
            return ResponseEntity.badRequest().body("La queja a editar no existe");
        }

        queja.setId(id);
        quejaService.guardarQueja(queja);
        return ResponseEntity.ok("Queja editada exitosamente");
    }


    @DeleteMapping("/eliminar/{id}")
    public void eliminarQueja(@PathVariable Long id) {
        quejaService.eliminarQuejaPorId(id);
    }
}
