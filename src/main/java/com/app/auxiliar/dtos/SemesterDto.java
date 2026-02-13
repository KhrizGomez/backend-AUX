package com.app.auxiliar.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SemesterDto {
    private Integer idSemestre;
    private String codigoPeriodo;
    private String nombrePeriodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaInicioMatriculas;
    private LocalDate fechaFinMatriculas;
    private Boolean estadoActivo;
    private Boolean esPeriodoActual;
}

