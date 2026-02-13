package com.app.auxiliar.services.implementation;

import com.app.auxiliar.dtos.SemesterDto;
import com.app.auxiliar.entities.Semester;
import com.app.auxiliar.repositories.ISemesterRepository;
import com.app.auxiliar.services.ISemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplementationSemesterService implements ISemesterService {
    private final ISemesterRepository semesterRepository;

    @Override
    public SemesterDto showSemesterById(Integer idSemestre) {
        Semester semester = semesterRepository.findById(idSemestre)
                .orElseThrow(() -> new RuntimeException("Semester not found with id: " + idSemestre));
        return convertToDto(semester);
    }

    @Override
    public SemesterDto createSemester(SemesterDto semesterDto) {
        Semester semester = new Semester();
        BeanUtils.copyProperties(semesterDto, semester);
        Semester savedSemester = semesterRepository.save(semester);
        return convertToDto(savedSemester);
    }

    @Override
    public SemesterDto updateSemester(Integer idSemestre, SemesterDto semesterDto) {
        Semester existingSemester = semesterRepository.findById(idSemestre)
                .orElseThrow(() -> new RuntimeException("Semester not found with id: " + idSemestre));
        BeanUtils.copyProperties(semesterDto, existingSemester);
        Semester updatedSemester = semesterRepository.save(existingSemester);
        return convertToDto(updatedSemester);
    }

    @Override
    public SemesterDto deleteSemester(Integer idSemestre) {
        Semester existingSemester = semesterRepository.findById(idSemestre)
                .orElseThrow(() -> new RuntimeException("Semester not found with id: " + idSemestre));
        semesterRepository.delete(existingSemester);
        return convertToDto(existingSemester);
    }

    private SemesterDto convertToDto(Semester semester) {
        SemesterDto dto = new SemesterDto();
        BeanUtils.copyProperties(semester, dto);
        return dto;
    }
}

