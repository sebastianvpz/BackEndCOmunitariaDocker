package com.gestion.encuesta.dto;

import lombok.Data;

@Data
public class ReportePropuestaDTO {
    private String fechaReporte;
    private String mensaje;
    private String tipoReporte;
    private Long usuarioId;
    private Long propuestaId;
}

