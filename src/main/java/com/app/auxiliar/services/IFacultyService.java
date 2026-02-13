package com.app.auxiliar.services;

import com.app.auxiliar.dtos.FacultyDto;

public interface IFacultyService {
    FacultyDto showFacultyById(Integer idFacultad);
    FacultyDto createFaculty(FacultyDto facultyDto);
    FacultyDto updateFaculty(Integer idFacultad, FacultyDto facultyDto);
    FacultyDto deleteFaculty(Integer idFacultad);
}
