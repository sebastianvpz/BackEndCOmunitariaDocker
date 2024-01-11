package com.gestion.encuesta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.encuesta.model.QuejaProblema;
import com.gestion.encuesta.repository.QuejaRepository;

@Service
public class QuejaService {
    @Autowired
    private QuejaRepository quejaRepository;

    public List<QuejaProblema> obtenerTodasLasQuejas() {
        return quejaRepository.findAll();
    }

    public QuejaProblema obtenerQuejaPorId(Long id) {
        return quejaRepository.findById(id).orElse(null);
    }

    public void guardarQueja(QuejaProblema queja) {
        quejaRepository.save(queja);
    }

    public void eliminarQuejaPorId(Long id) {
        quejaRepository.deleteById(id);
    }
}