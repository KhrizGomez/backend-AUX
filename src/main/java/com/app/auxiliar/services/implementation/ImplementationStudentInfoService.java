package com.app.auxiliar.services.implementation;

import com.app.auxiliar.dtos.StudentInfoDto;
import com.app.auxiliar.entities.Career;
import com.app.auxiliar.entities.Semester;
import com.app.auxiliar.entities.StudentInfo;
import com.app.auxiliar.entities.User;
import com.app.auxiliar.repositories.ICareerRepository;
import com.app.auxiliar.repositories.ISemesterRepository;
import com.app.auxiliar.repositories.IStudentInfoRepository;
import com.app.auxiliar.repositories.IUserRepository;
import com.app.auxiliar.services.IStudentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplementationStudentInfoService implements IStudentInfoService {
    private final IStudentInfoRepository studentInfoRepository;
    private final IUserRepository userRepository;
    private final ICareerRepository careerRepository;
    private final ISemesterRepository semesterRepository;

    @Override
    public StudentInfoDto showStudentInfoById(Integer idEstudiante) {
        StudentInfo studentInfo = studentInfoRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("StudentInfo not found with id: " + idEstudiante));
        return convertToDto(studentInfo);
    }

    @Override
    public StudentInfoDto createStudentInfo(StudentInfoDto studentInfoDto) {
        StudentInfo studentInfo = new StudentInfo();
        mapDtoToEntity(studentInfoDto, studentInfo);
        StudentInfo savedStudentInfo = studentInfoRepository.save(studentInfo);
        return convertToDto(savedStudentInfo);
    }

    @Override
    public StudentInfoDto updateStudentInfo(Integer idEstudiante, StudentInfoDto studentInfoDto) {
        StudentInfo existingStudentInfo = studentInfoRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("StudentInfo not found with id: " + idEstudiante));
        mapDtoToEntity(studentInfoDto, existingStudentInfo);
        StudentInfo updatedStudentInfo = studentInfoRepository.save(existingStudentInfo);
        return convertToDto(updatedStudentInfo);
    }

    @Override
    public StudentInfoDto deleteStudentInfo(Integer idEstudiante) {
        StudentInfo existingStudentInfo = studentInfoRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("StudentInfo not found with id: " + idEstudiante));
        studentInfoRepository.delete(existingStudentInfo);
        return convertToDto(existingStudentInfo);
    }

    private void mapDtoToEntity(StudentInfoDto dto, StudentInfo entity) {
        entity.setNumeroMatricula(dto.getNumeroMatricula());
        entity.setParalelo(dto.getParalelo());
        entity.setJornada(dto.getJornada());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setFechaEgreso(dto.getFechaEgreso());
        entity.setEstadoAcademico(dto.getEstadoAcademico());
        entity.setPromedioGeneral(dto.getPromedioGeneral());
        entity.setCreditosAprobados(dto.getCreditosAprobados());
        entity.setCreditosTotales(dto.getCreditosTotales());
        entity.setMatriculado(dto.getMatriculado());
        entity.setEsBecado(dto.getEsBecado());
        entity.setTipoBeca(dto.getTipoBeca());
        entity.setObservaciones(dto.getObservaciones());

        if (dto.getIdUsuario() != null) {
            User user = userRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getIdUsuario()));
            entity.setUser(user);
        }

        if (dto.getIdCarrera() != null) {
            Career career = careerRepository.findById(dto.getIdCarrera())
                    .orElseThrow(() -> new RuntimeException("Career not found with id: " + dto.getIdCarrera()));
            entity.setCareer(career);
        } else {
            entity.setCareer(null);
        }

        if (dto.getIdSemestreActual() != null) {
            Semester semester = semesterRepository.findById(dto.getIdSemestreActual())
                    .orElseThrow(() -> new RuntimeException("Semester not found with id: " + dto.getIdSemestreActual()));
            entity.setCurrentSemester(semester);
        } else {
            entity.setCurrentSemester(null);
        }
    }

    private StudentInfoDto convertToDto(StudentInfo studentInfo) {
        StudentInfoDto dto = new StudentInfoDto();
        dto.setIdEstudiante(studentInfo.getIdEstudiante());
        dto.setNumeroMatricula(studentInfo.getNumeroMatricula());
        dto.setParalelo(studentInfo.getParalelo());
        dto.setJornada(studentInfo.getJornada());
        dto.setFechaIngreso(studentInfo.getFechaIngreso());
        dto.setFechaEgreso(studentInfo.getFechaEgreso());
        dto.setEstadoAcademico(studentInfo.getEstadoAcademico());
        dto.setPromedioGeneral(studentInfo.getPromedioGeneral());
        dto.setCreditosAprobados(studentInfo.getCreditosAprobados());
        dto.setCreditosTotales(studentInfo.getCreditosTotales());
        dto.setMatriculado(studentInfo.getMatriculado());
        dto.setEsBecado(studentInfo.getEsBecado());
        dto.setTipoBeca(studentInfo.getTipoBeca());
        dto.setObservaciones(studentInfo.getObservaciones());
        if (studentInfo.getUser() != null) {
            dto.setIdUsuario(studentInfo.getUser().getIdUsuario());
        }
        if (studentInfo.getCareer() != null) {
            dto.setIdCarrera(studentInfo.getCareer().getIdCarrera());
        }
        if (studentInfo.getCurrentSemester() != null) {
            dto.setIdSemestreActual(studentInfo.getCurrentSemester().getIdSemestre());
        }
        return dto;
    }
}

