package com.gestion.encuesta.service;

import com.gestion.encuesta.model.Noticia;
import com.gestion.encuesta.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository repository;

    public List<Noticia> obtenerNoticias() {
        return repository.findAll();
    }

    public void guardarNoticia(Noticia noticia) {
        repository.save(noticia);
    }
}
