package com.gestion.encuesta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CambioContrasenaRequest {
    private String contrasenaAntigua;
    private String nuevaContrasena;
}
