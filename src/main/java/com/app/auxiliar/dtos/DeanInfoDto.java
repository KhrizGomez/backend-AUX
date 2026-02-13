package com.app.auxiliar.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DeanInfoDto {
    private Integer idDecano;
    private Integer idUsuario;
    private Integer idFacultad;
    private LocalDateTime fechaNombramiento;
    private LocalDate fechaFinPeriodo;
    private String resolucionNombramiento;
    private Boolean estado;
    private String observaciones;
}

