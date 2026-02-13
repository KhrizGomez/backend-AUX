package com.app.auxiliar.services;

import com.app.auxiliar.dtos.CareerDto;

public interface ICareerService {
    CareerDto showCareerById(Integer idCarrera);
    CareerDto createCareer(CareerDto careerDto);
    CareerDto updateCareer(Integer idCarrera, CareerDto careerDto);
    CareerDto deleteCareer(Integer idCarrera);
}

