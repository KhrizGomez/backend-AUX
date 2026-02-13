package com.app.auxiliar.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historial_matriculas", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_estudiante", "id_semestre"})
})
public class EnrollmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante", nullable = false)
    private StudentInfo student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre", nullable = false)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Career career;

    @Column(name = "paralelo")
    private String paralelo;

    @Column(name = "fecha_matricula")
    private LocalDateTime fechaMatricula;

    @Column(name = "tipo_matricula")
    private String tipoMatricula;

    @Column(name = "estado_matricula")
    private String estadoMatricula;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;
}

