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
    public ResponseEntity<?> listarVPropuestaPorId(@PathVariable Long id) {
        VotosPropuesta votosPropuesta = vPropuestaService.obtenerVPropuestaPorId(id);
        if (votosPropuesta != null) {
            return ResponseEntity.ok(votosPropuesta);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarVPropuesta(@RequestBody VotosPropuesta vPropuesta) {
        if (vPropuesta.getPropuesta() == null) {
            return ResponseEntity.badRequest().body("La propuesta no puede ser nula");
        }

        if (vPropuesta.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if (vPropuesta.getVoto() < 1 || vPropuesta.getVoto() > 5) {
            return ResponseEntity.badRequest().body("El voto debe estar entre 1 y 5");
        }

        vPropuestaService.guardarVPropuesta(vPropuesta);
        return ResponseEntity.ok("Voto a propuesta guardado exitosamente");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarVPropuesta(@PathVariable Long id, @RequestBody VotosPropuesta vPropuesta) {
        if (vPropuesta.getPropuesta() == null) {
            return ResponseEntity.badRequest().body("La propuesta no puede ser nula");
        }

        if (vPropuesta.getUsuario() == null) {
            return ResponseEntity.badRequest().body("El usuario no puede ser nulo");
        }

        if (vPropuesta.getVoto() < 1 || vPropuesta.getVoto() > 5) {
            return ResponseEntity.badRequest().body("El voto debe estar entre 1 y 5");
        }

        VotosPropuesta vPropuestaExistente = vPropuestaService.obtenerVPropuestaPorId(id);
        if (vPropuestaExistente == null) {
            return ResponseEntity.badRequest().body("La votación a la propuesta a editar no existe");
        }

        vPropuesta.setId(id);
        vPropuestaService.guardarVPropuesta(vPropuesta);
        return ResponseEntity.ok("Votación a propuesta editada exitosamente");
    }


    @DeleteMapping("/eliminar/{id}")
    public void eliminarVPropuesta(@PathVariable Long id) {
        vPropuestaService.eliminarVPropuestaPorId(id);
    }
}