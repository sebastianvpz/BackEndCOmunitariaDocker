package com.gestion.encuesta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.encuesta.model.Propuesta;
import com.gestion.encuesta.repository.PropuestaRepository;

@Service
public class PropuestaService {
	@Autowired
    private PropuestaRepository propuestaRepository;

    public List<Propuesta> obtenerTodasLasPropuestas() {
        return propuestaRepository.findAll();
    }

    public Propuesta obtenerPropuestaPorId(Long id) {
        return propuestaRepository.findById(id).orElse(null);
    }

    public void guardarPropuesta(Propuesta propuesta) {
        propuestaRepository.save(propuesta);
    }

    public void eliminarPropuestaPorId(Long id) {
        propuestaRepository.deleteById(id);
    }
}
