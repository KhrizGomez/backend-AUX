package com.app.auxiliar.services;

import com.app.auxiliar.dtos.EnrollmentHistoryDto;

public interface IEnrollmentHistoryService {
    EnrollmentHistoryDto showEnrollmentHistoryById(Integer idHistorial);
    EnrollmentHistoryDto createEnrollmentHistory(EnrollmentHistoryDto enrollmentHistoryDto);
    EnrollmentHistoryDto updateEnrollmentHistory(Integer idHistorial, EnrollmentHistoryDto enrollmentHistoryDto);
    EnrollmentHistoryDto deleteEnrollmentHistory(Integer idHistorial);
}

