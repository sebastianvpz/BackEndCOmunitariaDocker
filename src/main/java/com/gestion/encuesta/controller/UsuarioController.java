package com.gestion.encuesta.controller;

import com.gestion.encuesta.model.CambioContrasenaRequest;
import com.gestion.encuesta.model.Rol;
import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.service.RolService;
import com.gestion.encuesta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    @Autowired
    private RolService rolService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return service.obtenerTodosLosUsuarios();
    }
    @PostMapping("/guardar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
        Long idrol = 2L;
        Rol rol = rolService.getRolById(idrol).orElseThrow();
        if (usuario.getRol() == null) {
             usuario.setRol(rol);
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
    @GetMapping("/obtenerUsuarioPorId/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = service.obtenerUsuarioPorId(id);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/vetar/{id}")
    public ResponseEntity<?> vetarUsuario(@PathVariable Long id) {
        Usuario usuario = service.obtenerUsuarioPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuario.setVetado(true);
        service.guardarUsuario(usuario);
        return ResponseEntity.ok().body("{\"message\": \"Usuario vetado exitosamente\"}");
    }

    @PutMapping("/quitar-veto/{id}")
    public ResponseEntity<?> quitarVetoUsuario(@PathVariable Long id) {
        Usuario usuario = service.obtenerUsuarioPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuario.setVetado(false);
        service.guardarUsuario(usuario);
        return ResponseEntity.ok().body("{\"message\": \"Veto removido exitosamente\"}");
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = service.obtenerUsuarioPorId(id);

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar los campos modificables
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setUsername(usuario.getUsername());

        Usuario usuarioModificado = service.guardarUsuario(usuarioExistente);

        if (usuarioModificado != null) {
            return ResponseEntity.ok(usuarioModificado);
        } else {
            return ResponseEntity.badRequest().body("No se pudo modificar el usuario. Verificar campos.");
        }
    }

    @PutMapping("/cambiar-contraseña/{id}")
    public ResponseEntity<?> cambiarContraseña(@PathVariable Long id, @RequestBody CambioContrasenaRequest cambioContraseñaRequest) {
        Usuario usuario = service.obtenerUsuarioPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        // Verificar que la contraseña antigua coincida
        if (!passwordEncoder.matches(cambioContraseñaRequest.getContrasenaAntigua(), usuario.getPassword())) {
            return ResponseEntity.badRequest().body("La contraseña antigua no es correcta.");
        }

        // Encriptar y actualizar la contraseña
        String nuevaContraseñaEncriptada = passwordEncoder.encode(cambioContraseñaRequest.getNuevaContrasena());
        usuario.setPassword(nuevaContraseñaEncriptada);

        Usuario usuarioActualizado = service.guardarUsuario(usuario);

        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.badRequest().body("No se pudo cambiar la contraseña. Inténtalo nuevamente.");
        }
    }
}
