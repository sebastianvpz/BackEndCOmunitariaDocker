package com.gestion.encuesta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.encuesta.model.Propuesta;
import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.model.VotosPropuesta;
import com.gestion.encuesta.repository.UsuarioRepository;
import com.gestion.encuesta.repository.VPropuestaRepository;

@Service
public class VPropuestaService {
    @Autowired
    private VPropuestaRepository vPropuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PropuestaService propuestaService;
    
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    
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
    
    
    public void votarPropuesta(Long idPropuesta, Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        Propuesta propuesta = propuestaService.obtenerPropuestaPorId(idPropuesta);

        if (usuario != null && propuesta != null) {
            // Verifica si el usuario ya votó por la propuesta específica
            if (!vPropuestaRepository.existsByUsuarioIdAndPropuestaIdAndVotoPositivo(idUsuario, idPropuesta, true)) {
                VotosPropuesta voto = new VotosPropuesta();
                voto.setPropuesta(propuesta);
                voto.setUsuario(usuario);
                voto.setVotoPositivo(true); 

                vPropuestaRepository.save(voto);
            } else {
                // El usuario ya votó por esta propuesta
                throw new IllegalArgumentException("El usuario ya votó por esta propuesta.");
            }
        }
    }

    public void cancelarVotoPositivo(Long idPropuesta, Long idUsuario) {
    	System.out.println("Intentando cancelar voto positivo. idPropuesta: " + idPropuesta + ", idUsuario: " + idUsuario);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        Propuesta propuesta = propuestaService.obtenerPropuestaPorId(idPropuesta);

        if (usuario != null && propuesta != null) {
            VotosPropuesta voto = vPropuestaRepository.findByPropuestaAndUsuario(propuesta, usuario);

            if (voto != null && voto.isVotoPositivo()) {
                // Mensajes de depuración
                System.out.println("Voto encontrado y es positivo. Eliminando...");

                vPropuestaRepository.delete(voto);

                // Actualiza el estado de votado en el usuario
                usuarioRepository.save(usuario);
            } else {
                // Mensajes de depuración si el voto no es encontrado o no es positivo
                System.out.println("Voto no encontrado o no es positivo. No se realiza la cancelación.");
            }
        }
    }
    public int obtenerCantidadVotosPositivos(Long idPropuesta) {
        return vPropuestaRepository.sumarVotosPositivosPorPropuesta(idPropuesta);
    }
    
}