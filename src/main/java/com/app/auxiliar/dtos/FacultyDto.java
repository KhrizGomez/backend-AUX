package com.app.auxiliar.dtos;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FacultyDto {
    private Integer idFacultad;
    private String nombreFacultad;
    private String codigoFacultad;
    private String ubicacionOficina;
    private String telefonoOficina;
    private String emailFacultad;
    private LocalDate fechaCreacion;
    private Boolean estado;
}
