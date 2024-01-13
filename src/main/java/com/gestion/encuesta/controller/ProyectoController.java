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

import com.gestion.encuesta.model.Proyecto;
import com.gestion.encuesta.service.ProyectoService;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin("*")
public class ProyectoController {
    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/listar")
    public List<Proyecto> listarProyectos() {
        return proyectoService.obtenerTodosLosProyectos();
    }
    
    @GetMapping("/listar/{id}")
    public Proyecto listarProyectoPorId(@PathVariable Long id) {
        return proyectoService.obtenerProyectoPorId(id);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProyecto(@RequestBody Proyecto proyecto) {
        if (proyecto.getUrl() == null || proyecto.getUrl().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La URL del proyecto no puede estar en blanco");
        }

        if (proyecto.getTitulo() == null || proyecto.getTitulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del proyecto no puede estar en blanco");
        }

        if (proyecto.getDescripcion() == null || proyecto.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La descripción del proyecto no puede estar en blanco");
        }

        if (proyecto.getEstado() == null || proyecto.getEstado().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El estado del proyecto no puede estar en blanco");
        }


        proyectoService.guardarProyecto(proyecto);
        return ResponseEntity.ok("Proyecto guardado exitosamente");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarProyecto(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        if (proyecto.getUrl() == null || proyecto.getUrl().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La URL del proyecto no puede estar en blanco");
        }

        if (proyecto.getTitulo() == null || proyecto.getTitulo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El título del proyecto no puede estar en blanco");
        }

        if (proyecto.getDescripcion() == null || proyecto.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La descripción del proyecto no puede estar en blanco");
        }

        if (proyecto.getEstado() == null || proyecto.getEstado().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El estado del proyecto no puede estar en blanco");
        }

        Proyecto proyectoExistente = proyectoService.obtenerProyectoPorId(id);
        if (proyectoExistente == null) {
            return ResponseEntity.badRequest().body("El proyecto a editar no existe");
        }

        proyecto.setId(id);
        proyectoService.guardarProyecto(proyecto);
        return ResponseEntity.ok("Proyecto editado exitosamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyectoPorId(id);
    }
}
