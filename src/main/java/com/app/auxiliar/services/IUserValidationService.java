package com.app.auxiliar.services;

import com.app.auxiliar.dtos.UserValidationResponseDto;

public interface IUserValidationService {
    UserValidationResponseDto validateUserByCedula(String cedula);
}

