package com.app.auxiliar.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EnrollmentHistoryDto {
    private Integer idHistorial;
    private Integer idEstudiante;
    private Integer idSemestre;
    private Integer idCarrera;
    private String paralelo;
    private LocalDateTime fechaMatricula;
    private String tipoMatricula;
    private String estadoMatricula;
    private String observaciones;
}

