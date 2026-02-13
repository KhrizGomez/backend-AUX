package com.app.auxiliar.services;

import com.app.auxiliar.dtos.StudentInfoDto;

public interface IStudentInfoService {
    StudentInfoDto showStudentInfoById(Integer idEstudiante);
    StudentInfoDto createStudentInfo(StudentInfoDto studentInfoDto);
    StudentInfoDto updateStudentInfo(Integer idEstudiante, StudentInfoDto studentInfoDto);
    StudentInfoDto deleteStudentInfo(Integer idEstudiante);
}

