	package com.gestion.encuesta.controller;
	
	import java.util.Collections;
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
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;

import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.model.VotosPropuesta;
import com.gestion.encuesta.service.UsuarioService;
import com.gestion.encuesta.service.VPropuestaService;
	
	
	@RestController
	@RequestMapping("/vpropuestas")
	@CrossOrigin()
	public class VPropuestaController {
	    @Autowired
	    private VPropuestaService vPropuestaService;
	
	    @Autowired
	    private UsuarioService usuarioService;  // Asegúrate de tener el servicio de usuario inyectado

	    @GetMapping("/obtenerUsuario/{id}")
	    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
	        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);

	        if (usuario != null) {
	            return ResponseEntity.ok(usuario);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
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
	    
	    @PostMapping("/votar/{idPropuesta}/{idUsuario}")
	    public ResponseEntity<?> votarPropuesta(@PathVariable Long idPropuesta, @PathVariable Long idUsuario) {
	        vPropuestaService.votarPropuesta(idPropuesta, idUsuario);
	        String successMessage = "Voto a propuesta positivo guardado exitosamente";
	        return ResponseEntity.ok().body(Collections.singletonMap("message", successMessage));
	    }

	    @DeleteMapping("/cancelarVoto/{idPropuesta}/{idUsuario}")
	    public ResponseEntity<?> cancelarVoto(@PathVariable Long idPropuesta, @PathVariable Long idUsuario) {
	        vPropuestaService.cancelarVotoPositivo(idPropuesta, idUsuario);
	        String successMessage = "Voto a propuesta positivo cancelado exitosamente";
	        return ResponseEntity.ok().body(Collections.singletonMap("message", successMessage));
	    }
	    
	    @GetMapping("/cantidadVotosPositivos/{idPropuesta}")
	    public ResponseEntity<?> obtenerCantidadVotosPositivos(@PathVariable Long idPropuesta) {
	        int cantidadVotosPositivos = vPropuestaService.obtenerCantidadVotosPositivos(idPropuesta);
	        return ResponseEntity.ok(Collections.singletonMap("cantidadVotosPositivos", cantidadVotosPositivos));
	    }
	}