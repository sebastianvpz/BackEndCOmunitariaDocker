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
    public ParticipacionEvento listarPEventoPorId(@PathVariable Long id) {
        return pEventoService.obtenerPEventoPorId(id);
    }
    
    @PostMapping("/guardar")
    public void guardarPEvento(@RequestBody ParticipacionEvento pEvento) {
        pEventoService.guardarPEvento(pEvento);
    }
    
    @PutMapping("/editar/{id}")
    public void editarPEvento(@PathVariable Long id, @RequestBody ParticipacionEvento pEvento) {
        pEvento.setId(id);
        pEventoService.guardarPEvento(pEvento);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPEvento(@PathVariable Long id) {
        pEventoService.eliminarPEventoPorId(id);
    }
}