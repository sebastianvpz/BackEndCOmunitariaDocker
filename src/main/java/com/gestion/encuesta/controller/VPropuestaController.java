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

import com.gestion.encuesta.model.VotosPropuesta;
import com.gestion.encuesta.service.VPropuestaService;

@RestController
@RequestMapping("/vpropuestas")
@CrossOrigin("*")
public class VPropuestaController {
    @Autowired
    private VPropuestaService vPropuestaService;

    @GetMapping("/listar")
    public List<VotosPropuesta> listarVPropuestas() {
        return vPropuestaService.obtenerTodosLosVPropuestas();
    }
    
    @GetMapping("/listar/{id}")
    public VotosPropuesta listarVPropuestaPorId(@PathVariable Long id) {
        return vPropuestaService.obtenerVPropuestaPorId(id);
    }
    
    @PostMapping("/guardar")
    public void guardarVPropuesta(@RequestBody VotosPropuesta vPropuesta) {
        vPropuestaService.guardarVPropuesta(vPropuesta);
    }
    
    @PutMapping("/editar/{id}")
    public void editarVPropuesta(@PathVariable Long id, @RequestBody VotosPropuesta vPropuesta) {
        vPropuesta.setId(id);
        vPropuestaService.guardarVPropuesta(vPropuesta);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarVPropuesta(@PathVariable Long id) {
        vPropuestaService.eliminarVPropuestaPorId(id);
    }
}