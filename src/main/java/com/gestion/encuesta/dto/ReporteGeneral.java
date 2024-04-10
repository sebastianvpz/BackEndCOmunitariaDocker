package com.gestion.encuesta.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReporteGeneral {
    private Long id;
    private String fechaReporte;
    private String mensaje;
    private String tipoReporte;
}
