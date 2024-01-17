package com.gestion.encuesta.controller;

import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @PostMapping("/guardar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
    	 if (usuario.getRol() == null) {
             usuario.setRol("USER");
         }
    	String username = usuario.getUsername();
        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();
        String email = usuario.getEmail();
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(contraseñaEncriptada);
        
        Usuario usuarioGuardado = service.guardarUsuario(usuario);

        if (usuarioGuardado != null) {
            return ResponseEntity.ok(usuarioGuardado);
        }else {
            return ResponseEntity.badRequest().body("No se pudo guardar el usuario. Verficar campos.");
        }
    }
}
