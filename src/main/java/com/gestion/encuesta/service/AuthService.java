package com.gestion.encuesta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.repository.UsuarioRepository;


@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticar(String username, String password) {
    	return usuarioRepository.findByUsernameAndPassword
    			(username, password);
    }
}