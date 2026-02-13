package com.app.auxiliar.services.implementation;

import com.app.auxiliar.dtos.EnrollmentHistoryDto;
import com.app.auxiliar.entities.Career;
import com.app.auxiliar.entities.EnrollmentHistory;
import com.app.auxiliar.entities.Semester;
import com.app.auxiliar.entities.StudentInfo;
import com.app.auxiliar.repositories.ICareerRepository;
import com.app.auxiliar.repositories.IEnrollmentHistoryRepository;
import com.app.auxiliar.repositories.ISemesterRepository;
import com.app.auxiliar.repositories.IStudentInfoRepository;
import com.app.auxiliar.services.IEnrollmentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplementationEnrollmentHistoryService implements IEnrollmentHistoryService {
    private final IEnrollmentHistoryRepository enrollmentHistoryRepository;
    private final IStudentInfoRepository studentInfoRepository;
    private final ISemesterRepository semesterRepository;
    private final ICareerRepository careerRepository;

    @Override
    public EnrollmentHistoryDto showEnrollmentHistoryById(Integer idHistorial) {
        EnrollmentHistory enrollmentHistory = enrollmentHistoryRepository.findById(idHistorial)
                .orElseThrow(() -> new RuntimeException("EnrollmentHistory not found with id: " + idHistorial));
        return convertToDto(enrollmentHistory);
    }

    @Override
    public EnrollmentHistoryDto createEnrollmentHistory(EnrollmentHistoryDto enrollmentHistoryDto) {
        EnrollmentHistory enrollmentHistory = new EnrollmentHistory();
        mapDtoToEntity(enrollmentHistoryDto, enrollmentHistory);
        EnrollmentHistory savedEnrollmentHistory = enrollmentHistoryRepository.save(enrollmentHistory);
        return convertToDto(savedEnrollmentHistory);
    }

    @Override
    public EnrollmentHistoryDto updateEnrollmentHistory(Integer idHistorial, EnrollmentHistoryDto enrollmentHistoryDto) {
        EnrollmentHistory existingEnrollmentHistory = enrollmentHistoryRepository.findById(idHistorial)
                .orElseThrow(() -> new RuntimeException("EnrollmentHistory not found with id: " + idHistorial));
        mapDtoToEntity(enrollmentHistoryDto, existingEnrollmentHistory);
        EnrollmentHistory updatedEnrollmentHistory = enrollmentHistoryRepository.save(existingEnrollmentHistory);
        return convertToDto(updatedEnrollmentHistory);
    }

    @Override
    public EnrollmentHistoryDto deleteEnrollmentHistory(Integer idHistorial) {
        EnrollmentHistory existingEnrollmentHistory = enrollmentHistoryRepository.findById(idHistorial)
                .orElseThrow(() -> new RuntimeException("EnrollmentHistory not found with id: " + idHistorial));
        enrollmentHistoryRepository.delete(existingEnrollmentHistory);
        return convertToDto(existingEnrollmentHistory);
    }

    private void mapDtoToEntity(EnrollmentHistoryDto dto, EnrollmentHistory entity) {
        entity.setParalelo(dto.getParalelo());
        entity.setFechaMatricula(dto.getFechaMatricula());
        entity.setTipoMatricula(dto.getTipoMatricula());
        entity.setEstadoMatricula(dto.getEstadoMatricula());
        entity.setObservaciones(dto.getObservaciones());

        if (dto.getIdEstudiante() != null) {
            StudentInfo student = studentInfoRepository.findById(dto.getIdEstudiante())
                    .orElseThrow(() -> new RuntimeException("StudentInfo not found with id: " + dto.getIdEstudiante()));
            entity.setStudent(student);
        }

        if (dto.getIdSemestre() != null) {
            Semester semester = semesterRepository.findById(dto.getIdSemestre())
                    .orElseThrow(() -> new RuntimeException("Semester not found with id: " + dto.getIdSemestre()));
            entity.setSemester(semester);
        }

        if (dto.getIdCarrera() != null) {
            Career career = careerRepository.findById(dto.getIdCarrera())
                    .orElseThrow(() -> new RuntimeException("Career not found with id: " + dto.getIdCarrera()));
            entity.setCareer(career);
        } else {
            entity.setCareer(null);
        }
    }

    private EnrollmentHistoryDto convertToDto(EnrollmentHistory enrollmentHistory) {
        EnrollmentHistoryDto dto = new EnrollmentHistoryDto();
        dto.setIdHistorial(enrollmentHistory.getIdHistorial());
        dto.setParalelo(enrollmentHistory.getParalelo());
        dto.setFechaMatricula(enrollmentHistory.getFechaMatricula());
        dto.setTipoMatricula(enrollmentHistory.getTipoMatricula());
        dto.setEstadoMatricula(enrollmentHistory.getEstadoMatricula());
        dto.setObservaciones(enrollmentHistory.getObservaciones());
        if (enrollmentHistory.getStudent() != null) {
            dto.setIdEstudiante(enrollmentHistory.getStudent().getIdEstudiante());
        }
        if (enrollmentHistory.getSemester() != null) {
            dto.setIdSemestre(enrollmentHistory.getSemester().getIdSemestre());
        }
        if (enrollmentHistory.getCareer() != null) {
            dto.setIdCarrera(enrollmentHistory.getCareer().getIdCarrera());
        }
        return dto;
    }
}

