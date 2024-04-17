package com.gestion.encuesta.controller;

import com.gestion.encuesta.model.Noticia;
import com.gestion.encuesta.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticia")
@CrossOrigin("*")
public class NoticiaController {

    @Autowired
    private NoticiaService service;

    @GetMapping("/listar")
    public List<Noticia> listarNoticias() {
        return service.obtenerNoticias();
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> guardarEvento(@RequestParam("img") String imgString64,
                                           @RequestParam("titulo") String titulo,
                                           @RequestParam("descripcion") String descripcion,
                                           @RequestParam("fechaHora") String fechaHora) {
        try {
            // Aquí puedes realizar validaciones adicionales si es necesario

            if (titulo == null || titulo.trim().isEmpty() ||
                    fechaHora == null || fechaHora.trim().isEmpty() ||
                    descripcion == null || descripcion.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Los campos título, fechaHora y descripcion no pueden estar vacíos");
            }

            Noticia noticia = new Noticia();
            noticia.setTitulo(titulo);
            noticia.setDescripcion(descripcion);
            noticia.setFechaHora(fechaHora);
            noticia.setImg(imgString64);
            service.guardarNoticia(noticia);

            return ResponseEntity.ok().body("{\"message\": \"Noticia guardado exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar el archivo: " + e.getMessage());
        }
    }

}
