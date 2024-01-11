package com.gestion.encuesta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.encuesta.model.VotosPropuesta;
import com.gestion.encuesta.repository.VPropuestaRepository;

@Service
public class VPropuestaService {
    @Autowired
    private VPropuestaRepository vPropuestaRepository;

    public List<VotosPropuesta> obtenerTodosLosVPropuestas() {
        return vPropuestaRepository.findAll();
    }

    public VotosPropuesta obtenerVPropuestaPorId(Long id) {
        return vPropuestaRepository.findById(id).orElse(null);
    }

    public void guardarVPropuesta(VotosPropuesta vPropuesta) {
        vPropuestaRepository.save(vPropuesta);
    }

    public void eliminarVPropuestaPorId(Long id) {
        vPropuestaRepository.deleteById(id);
    }
}