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
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EventoService eventoService;

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
    public List<ParticipacionEvento> obtenerParticipacionesPorIdDeEvento(Long eventoId) {
        return pEventoRepository.findByEventoId(eventoId);
    }
    
    public List<ParticipacionEvento> obtenerParticipacionesPorIdDeUsuario(Long usuarioId) {
        // Llamar al repositorio para obtener las participaciones por ID de usuario
        return pEventoRepository.findByUsuarioId(usuarioId);
    }
    
    public ParticipacionEvento obtenerParticipacionPorUsuarioYEvento(Long usuarioId, Long eventoId) {
        // Realizar una consulta personalizada para obtener la participación por ID de usuario y ID de evento
        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId);
        Evento evento = eventoService.obtenerEventoPorId(eventoId);
        
        if (usuario != null && evento != null) {
            return pEventoRepository.findByUsuarioAndEvento(usuario, evento);
        } else {
            return null;
        }
    }
}