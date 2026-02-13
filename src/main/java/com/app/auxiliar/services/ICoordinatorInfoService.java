package com.app.auxiliar.services;

import com.app.auxiliar.dtos.CoordinatorInfoDto;

public interface ICoordinatorInfoService {
    CoordinatorInfoDto showCoordinatorInfoById(Integer idCoordinador);
    CoordinatorInfoDto createCoordinatorInfo(CoordinatorInfoDto coordinatorInfoDto);
    CoordinatorInfoDto updateCoordinatorInfo(Integer idCoordinador, CoordinatorInfoDto coordinatorInfoDto);
    CoordinatorInfoDto deleteCoordinatorInfo(Integer idCoordinador);
}

