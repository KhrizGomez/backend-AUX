package com.app.auxiliar.services.implementation;

import com.app.auxiliar.dtos.FacultyDto;
import com.app.auxiliar.entities.Faculty;
import com.app.auxiliar.repositories.IFacultyRepository;
import com.app.auxiliar.services.IFacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplementationFacultyService implements IFacultyService {
    private final IFacultyRepository facultyRepository;

    @Override
    public FacultyDto showFacultyById(Integer idFacultad) {
        Faculty faculty = facultyRepository.findById(idFacultad)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + idFacultad));
        return convertToDto(faculty);
    }

    @Override
    public FacultyDto createFaculty(FacultyDto facultyDto) {
        Faculty faculty = new Faculty();
        BeanUtils.copyProperties(facultyDto, faculty);
        Faculty savedFaculty = facultyRepository.save(faculty);
        return convertToDto(savedFaculty);
    }

    @Override
    public FacultyDto updateFaculty(Integer idFacultad, FacultyDto facultyDto) {
        Faculty existingFaculty = facultyRepository.findById(idFacultad)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + idFacultad));
        BeanUtils.copyProperties(facultyDto, existingFaculty);
        Faculty updatedFaculty = facultyRepository.save(existingFaculty);
        return convertToDto(updatedFaculty);
    }

    @Override
    public FacultyDto deleteFaculty(Integer idFacultad) {
        Faculty existingFaculty = facultyRepository.findById(idFacultad)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + idFacultad));
        facultyRepository.delete(existingFaculty);
        return convertToDto(existingFaculty);
    }

    public FacultyDto convertToDto(Faculty faculty) {
        FacultyDto facultyDto = new FacultyDto();
        BeanUtils.copyProperties(faculty, facultyDto);
        return facultyDto;
    }
}
