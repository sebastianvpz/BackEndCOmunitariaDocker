package com.gestion.encuesta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.encuesta.model.Proyecto;
import com.gestion.encuesta.repository.ProyectoRepository;

@Service
public class ProyectoService {
    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> obtenerTodosLosProyectos() {
        return proyectoRepository.findAll();
    }

    public Proyecto obtenerProyectoPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    public void guardarProyecto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    public void eliminarProyectoPorId(Long id) {
        proyectoRepository.deleteById(id);
    }
}
