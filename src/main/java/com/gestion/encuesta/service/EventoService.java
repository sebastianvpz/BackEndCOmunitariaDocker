package com.gestion.encuesta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.encuesta.model.Evento;
import com.gestion.encuesta.repository.EventoRepository;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    public Evento obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public void guardarEvento(Evento evento) {
        eventoRepository.save(evento);
    }


    public void eliminarEventoPorId(Long id) {
        eventoRepository.deleteById(id);
    }
}