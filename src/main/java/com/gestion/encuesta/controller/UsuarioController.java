package com.gestion.encuesta.controller;

import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/guardar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
        String username = usuario.getUsername();
        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();
        String email = usuario.getEmail();
        String password = usuario.getPassword();

        Usuario usuarioGuardado = service.guardarUsuario(usuario);

        if (usuarioGuardado != null) {
            return ResponseEntity.ok(usuarioGuardado);
        }else {
            return ResponseEntity.badRequest().body("No se pudo guardar el usuario. Verficar campos.");
        }
    }
}
