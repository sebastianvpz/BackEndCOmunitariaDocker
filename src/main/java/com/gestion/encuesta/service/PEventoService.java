package com.gestion.encuesta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.encuesta.model.Evento;
import com.gestion.encuesta.model.ParticipacionEvento;
import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.repository.PEventoRepository;

@Service
public class PEventoService {
    @Autowired
    private PEventoRepository pEventoRepository;

    public ParticipacionEvento obtenerParticipacionPorUsuarioYEvento(Usuario usuario, Evento evento) {
        // Realizar una consulta personalizada para obtener la participación por usuario y evento
        return pEventoRepository.findByUsuarioAndEvento(usuario, evento);
    }
    
    public List<ParticipacionEvento> obtenerTodosLosPEventos() {
        return pEventoRepository.findAll();
    }

    public ParticipacionEvento obtenerPEventoPorId(Long id) {
        return pEventoRepository.findById(id).orElse(null);
    }

    public void guardarPEvento(ParticipacionEvento pEvento) {
        pEventoRepository.save(pEvento);
    }

    public void eliminarPEventoPorId(Long id) {
        pEventoRepository.deleteById(id);
    }
}