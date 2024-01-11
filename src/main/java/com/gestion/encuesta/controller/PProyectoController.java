package com.gestion.encuesta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ParticipacionProyecto listarPProyectoPorId(@PathVariable Long id) {
        return pProyectoService.obtenerPProyectoPorId(id);
    }
    
    @PostMapping("/guardar")
    public void guardarPProyecto(@RequestBody ParticipacionProyecto pProyecto) {
        pProyectoService.guardarPProyecto(pProyecto);
    }
    
    @PutMapping("/editar/{id}")
    public void editarPProyecto(@PathVariable Long id, @RequestBody ParticipacionProyecto pProyecto) {
        pProyecto.setId(id);
        pProyectoService.guardarPProyecto(pProyecto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPProyecto(@PathVariable Long id) {
        pProyectoService.eliminarPProyectoPorId(id);
    }
}
