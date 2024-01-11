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

import com.gestion.encuesta.model.Evento;
import com.gestion.encuesta.service.EventoService;

@RestController
@RequestMapping("/eventos")
@CrossOrigin("*")
public class EventoController {
	@Autowired
    private EventoService eventoService;

    @GetMapping("/listar")
    public List<Evento> listarEventos() {
        return eventoService.obtenerTodosLosEventos();
    }
    
    @GetMapping("/listar/{id}")
    public Evento listarEventoPorId(@PathVariable Long id) {
        return eventoService.obtenerEventoPorId(id);
    }
    
    @PostMapping("/guardar")
    public void guardarEvento(@RequestBody Evento evento) {
        eventoService.guardarEvento(evento);
    }
    
    @PutMapping("/editar/{id}")
    public void editarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        evento.setId(id);
        eventoService.guardarEvento(evento);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarEvento(@PathVariable Long id) {
        eventoService.eliminarEventoPorId(id);
    }
}
