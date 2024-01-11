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
    public void guardarProyecto(@RequestBody Proyecto proyecto) {
        proyectoService.guardarProyecto(proyecto);
    }
    
    @PutMapping("/editar/{id}")
    public void editarProyecto(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        proyecto.setId(id);
        proyectoService.guardarProyecto(proyecto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyectoPorId(id);
    }
}
