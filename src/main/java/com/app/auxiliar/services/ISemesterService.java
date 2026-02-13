package com.app.auxiliar.services;

import com.app.auxiliar.dtos.SemesterDto;

public interface ISemesterService {
    SemesterDto showSemesterById(Integer idSemestre);
    SemesterDto createSemester(SemesterDto semesterDto);
    SemesterDto updateSemester(Integer idSemestre, SemesterDto semesterDto);
    SemesterDto deleteSemester(Integer idSemestre);
}

