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

import com.gestion.encuesta.model.Propuesta;
import com.gestion.encuesta.service.PropuestaService;

@RestController
@RequestMapping("/propuestas")
@CrossOrigin("*")
public class PropuestaController {

    @Autowired
    private PropuestaService propuestaService;

    @GetMapping("/listar")
    public List<Propuesta> listarPropuestas() {
        return propuestaService.obtenerTodasLasPropuestas();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPropuestaPorId(@PathVariable Long id) {
        Propuesta propuesta = propuestaService.obtenerPropuestaPorId(id);
        if (propuesta != null) {
            return ResponseEntity.ok(propuesta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPropuesta(@RequestBody Propuesta propuesta) {
        // Validaciones manuales
        if (propuesta.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if (propuesta.getUrl() == null || propuesta.getUrl().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La URL no puede estar en blanco");
        }

        if (propuesta.getTitulo() == null || propuesta.getTitulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título no puede estar en blanco");
        }

        if (propuesta.getDescripcion() == null || propuesta.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La descripción no puede estar en blanco");
        }

        // Otras validaciones según sea necesario

        propuestaService.guardarPropuesta(propuesta);
        return ResponseEntity.ok("Propuesta guardada exitosamente");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPropuesta(@PathVariable Long id, @RequestBody Propuesta propuesta) {
        // Validaciones manuales
        if (propuesta.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if (propuesta.getUrl() == null || propuesta.getUrl().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La URL no puede estar en blanco");
        }

        if (propuesta.getTitulo() == null || propuesta.getTitulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título no puede estar en blanco");
        }

        if (propuesta.getDescripcion() == null || propuesta.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La descripción no puede estar en blanco");
        }

        // Otras validaciones según sea necesario

        Propuesta propuestaExistente = propuestaService.obtenerPropuestaPorId(id);
        if (propuestaExistente == null) {
            return ResponseEntity.badRequest().body("La propuesta a editar no existe");
        }

        propuesta.setId(id);
        propuestaService.guardarPropuesta(propuesta);
        return ResponseEntity.ok("Propuesta editada exitosamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPropuesta(@PathVariable Long id) {
        Propuesta propuestaExistente = propuestaService.obtenerPropuestaPorId(id);
        if (propuestaExistente == null) {
            return ResponseEntity.badRequest().body("La propuesta a eliminar no existe");
        }

        propuestaService.eliminarPropuestaPorId(id);
        return ResponseEntity.ok("Propuesta eliminada exitosamente");
    }
}