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

import com.gestion.encuesta.model.ParticipacionProyecto;
import com.gestion.encuesta.service.PProyectoService;

@RestController
@RequestMapping("/pproyectos")
@CrossOrigin("*")
public class PProyectoController {
    @Autowired
    private PProyectoService pProyectoService;

    @GetMapping("/listar")
    public List<ParticipacionProyecto> listarPProyectos() {
        return pProyectoService.obtenerTodosLosPProyectos();
    }
    
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPProyectoPorId(@PathVariable Long id) {
        ParticipacionProyecto participacionProyecto = pProyectoService.obtenerPProyectoPorId(id);
        if (participacionProyecto != null) {
            return ResponseEntity.ok(participacionProyecto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPProyecto(@RequestBody ParticipacionProyecto pProyecto) {
        if (pProyecto.getProyecto() == null) {
            return ResponseEntity.badRequest().body("El proyecto no puede ser nulo");
        }

        if (pProyecto.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if (pProyecto.getRol() == null || pProyecto.getRol().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El rol de participación no puede estar en blanco");
        }


        pProyectoService.guardarPProyecto(pProyecto);
        return ResponseEntity.ok("Participación en proyecto guardada exitosamente");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPProyecto(@PathVariable Long id, @RequestBody ParticipacionProyecto pProyecto) {
        if (pProyecto.getProyecto() == null) {
            return ResponseEntity.badRequest().body("El proyecto no puede ser nulo");
        }

        if (pProyecto.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if (pProyecto.getRol() == null || pProyecto.getRol().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El rol de participación no puede estar en blanco");
        }
        ParticipacionProyecto pproyectoExistente = pProyectoService.obtenerPProyectoPorId(id);
        if (pproyectoExistente == null) {
            return ResponseEntity.badRequest().body("La participacion de proyecto a editar no existe");
        }

        pProyecto.setId(id);
        pProyectoService.guardarPProyecto(pProyecto);
        return ResponseEntity.ok("Participación en proyecto editada exitosamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPProyecto(@PathVariable Long id) {
        pProyectoService.eliminarPProyectoPorId(id);
    }
}
