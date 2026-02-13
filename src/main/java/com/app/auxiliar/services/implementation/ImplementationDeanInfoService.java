package com.app.auxiliar.services.implementation;

import com.app.auxiliar.dtos.DeanInfoDto;
import com.app.auxiliar.entities.DeanInfo;
import com.app.auxiliar.entities.Faculty;
import com.app.auxiliar.entities.User;
import com.app.auxiliar.repositories.IDeanInfoRepository;
import com.app.auxiliar.repositories.IFacultyRepository;
import com.app.auxiliar.repositories.IUserRepository;
import com.app.auxiliar.services.IDeanInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplementationDeanInfoService implements IDeanInfoService {
    private final IDeanInfoRepository deanInfoRepository;
    private final IUserRepository userRepository;
    private final IFacultyRepository facultyRepository;

    @Override
    public DeanInfoDto showDeanInfoById(Integer idDecano) {
        DeanInfo deanInfo = deanInfoRepository.findById(idDecano)
                .orElseThrow(() -> new RuntimeException("DeanInfo not found with id: " + idDecano));
        return convertToDto(deanInfo);
    }

    @Override
    public DeanInfoDto createDeanInfo(DeanInfoDto deanInfoDto) {
        DeanInfo deanInfo = new DeanInfo();
        mapDtoToEntity(deanInfoDto, deanInfo);
        DeanInfo savedDeanInfo = deanInfoRepository.save(deanInfo);
        return convertToDto(savedDeanInfo);
    }

    @Override
    public DeanInfoDto updateDeanInfo(Integer idDecano, DeanInfoDto deanInfoDto) {
        DeanInfo existingDeanInfo = deanInfoRepository.findById(idDecano)
                .orElseThrow(() -> new RuntimeException("DeanInfo not found with id: " + idDecano));
        mapDtoToEntity(deanInfoDto, existingDeanInfo);
        DeanInfo updatedDeanInfo = deanInfoRepository.save(existingDeanInfo);
        return convertToDto(updatedDeanInfo);
    }

    @Override
    public DeanInfoDto deleteDeanInfo(Integer idDecano) {
        DeanInfo existingDeanInfo = deanInfoRepository.findById(idDecano)
                .orElseThrow(() -> new RuntimeException("DeanInfo not found with id: " + idDecano));
        deanInfoRepository.delete(existingDeanInfo);
        return convertToDto(existingDeanInfo);
    }

    private void mapDtoToEntity(DeanInfoDto dto, DeanInfo entity) {
        entity.setFechaNombramiento(dto.getFechaNombramiento());
        entity.setFechaFinPeriodo(dto.getFechaFinPeriodo());
        entity.setResolucionNombramiento(dto.getResolucionNombramiento());
        entity.setEstado(dto.getEstado());
        entity.setObservaciones(dto.getObservaciones());

        if (dto.getIdUsuario() != null) {
            User user = userRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getIdUsuario()));
            entity.setUser(user);
        }

        if (dto.getIdFacultad() != null) {
            Faculty faculty = facultyRepository.findById(dto.getIdFacultad())
                    .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + dto.getIdFacultad()));
            entity.setFaculty(faculty);
        } else {
            entity.setFaculty(null);
        }
    }

    private DeanInfoDto convertToDto(DeanInfo deanInfo) {
        DeanInfoDto dto = new DeanInfoDto();
        dto.setIdDecano(deanInfo.getIdDecano());
        dto.setFechaNombramiento(deanInfo.getFechaNombramiento());
        dto.setFechaFinPeriodo(deanInfo.getFechaFinPeriodo());
        dto.setResolucionNombramiento(deanInfo.getResolucionNombramiento());
        dto.setEstado(deanInfo.getEstado());
        dto.setObservaciones(deanInfo.getObservaciones());
        if (deanInfo.getUser() != null) {
            dto.setIdUsuario(deanInfo.getUser().getIdUsuario());
        }
        if (deanInfo.getFaculty() != null) {
            dto.setIdFacultad(deanInfo.getFaculty().getIdFacultad());
        }
        return dto;
    }
}

