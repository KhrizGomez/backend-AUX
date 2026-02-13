package com.app.auxiliar.services;

import com.app.auxiliar.dtos.DeanInfoDto;

public interface IDeanInfoService {
    DeanInfoDto showDeanInfoById(Integer idDecano);
    DeanInfoDto createDeanInfo(DeanInfoDto deanInfoDto);
    DeanInfoDto updateDeanInfo(Integer idDecano, DeanInfoDto deanInfoDto);
    DeanInfoDto deleteDeanInfo(Integer idDecano);
}

