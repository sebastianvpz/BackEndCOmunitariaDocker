package com.gestion.encuesta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.encuesta.model.ParticipacionProyecto;
import com.gestion.encuesta.repository.PProyectoRepository;

@Service
public class PProyectoService {
    @Autowired
    private PProyectoRepository pProyectoRepository;

    public List<ParticipacionProyecto> obtenerTodosLosPProyectos() {
        return pProyectoRepository.findAll();
    }

    public ParticipacionProyecto obtenerPProyectoPorId(Long id) {
        return pProyectoRepository.findById(id).orElse(null);
    }

    public void guardarPProyecto(ParticipacionProyecto pProyecto) {
        pProyectoRepository.save(pProyecto);
    }

    public void eliminarPProyectoPorId(Long id) {
        pProyectoRepository.deleteById(id);
    }
}