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
    public QuejaProblema listarQuejaPorId(@PathVariable Long id) {
        return quejaService.obtenerQuejaPorId(id);
    }
    
    @PostMapping("/guardar")
    public void guardarQueja(@RequestBody QuejaProblema queja) {
        quejaService.guardarQueja(queja);
    }
    
    @PutMapping("/editar/{id}")
    public void editarQueja(@PathVariable Long id, @RequestBody QuejaProblema queja) {
        queja.setId(id);
        quejaService.guardarQueja(queja);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarQueja(@PathVariable Long id) {
        quejaService.eliminarQuejaPorId(id);
    }
}
