package com.gestion.encuesta.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReporteQuejaDTO {

    private String fechaReporte;
    private String mensaje;
    private String tipoReporte;
    private Long usuarioId;
    private Long quejaId;
}
