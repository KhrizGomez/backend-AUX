package com.app.auxiliar.services.implementation;

import com.app.auxiliar.dtos.CoordinatorInfoDto;
import com.app.auxiliar.entities.Career;
import com.app.auxiliar.entities.CoordinatorInfo;
import com.app.auxiliar.entities.User;
import com.app.auxiliar.repositories.ICareerRepository;
import com.app.auxiliar.repositories.ICoordinatorInfoRepository;
import com.app.auxiliar.repositories.IUserRepository;
import com.app.auxiliar.services.ICoordinatorInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplementationCoordinatorInfoService implements ICoordinatorInfoService {
    private final ICoordinatorInfoRepository coordinatorInfoRepository;
    private final IUserRepository userRepository;
    private final ICareerRepository careerRepository;

    @Override
    public CoordinatorInfoDto showCoordinatorInfoById(Integer idCoordinador) {
        CoordinatorInfo coordinatorInfo = coordinatorInfoRepository.findById(idCoordinador)
                .orElseThrow(() -> new RuntimeException("CoordinatorInfo not found with id: " + idCoordinador));
        return convertToDto(coordinatorInfo);
    }

    @Override
    public CoordinatorInfoDto createCoordinatorInfo(CoordinatorInfoDto coordinatorInfoDto) {
        CoordinatorInfo coordinatorInfo = new CoordinatorInfo();
        mapDtoToEntity(coordinatorInfoDto, coordinatorInfo);
        CoordinatorInfo savedCoordinatorInfo = coordinatorInfoRepository.save(coordinatorInfo);
        return convertToDto(savedCoordinatorInfo);
    }

    @Override
    public CoordinatorInfoDto updateCoordinatorInfo(Integer idCoordinador, CoordinatorInfoDto coordinatorInfoDto) {
        CoordinatorInfo existingCoordinatorInfo = coordinatorInfoRepository.findById(idCoordinador)
                .orElseThrow(() -> new RuntimeException("CoordinatorInfo not found with id: " + idCoordinador));
        mapDtoToEntity(coordinatorInfoDto, existingCoordinatorInfo);
        CoordinatorInfo updatedCoordinatorInfo = coordinatorInfoRepository.save(existingCoordinatorInfo);
        return convertToDto(updatedCoordinatorInfo);
    }

    @Override
    public CoordinatorInfoDto deleteCoordinatorInfo(Integer idCoordinador) {
        CoordinatorInfo existingCoordinatorInfo = coordinatorInfoRepository.findById(idCoordinador)
                .orElseThrow(() -> new RuntimeException("CoordinatorInfo not found with id: " + idCoordinador));
        coordinatorInfoRepository.delete(existingCoordinatorInfo);
        return convertToDto(existingCoordinatorInfo);
    }

    private void mapDtoToEntity(CoordinatorInfoDto dto, CoordinatorInfo entity) {
        entity.setOficinaAtencion(dto.getOficinaAtencion());
        entity.setHorarioAtencion(dto.getHorarioAtencion());
        entity.setExtensionTelefonica(dto.getExtensionTelefonica());
        entity.setFechaNombramiento(dto.getFechaNombramiento());
        entity.setFechaFinPeriodo(dto.getFechaFinPeriodo());
        entity.setEstado(dto.getEstado());
        entity.setObservaciones(dto.getObservaciones());

        if (dto.getIdUsuario() != null) {
            User user = userRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getIdUsuario()));
            entity.setUser(user);
        }

        if (dto.getIdCarrera() != null) {
            Career career = careerRepository.findById(dto.getIdCarrera())
                    .orElseThrow(() -> new RuntimeException("Career not found with id: " + dto.getIdCarrera()));
            entity.setCareer(career);
        } else {
            entity.setCareer(null);
        }
    }

    private CoordinatorInfoDto convertToDto(CoordinatorInfo coordinatorInfo) {
        CoordinatorInfoDto dto = new CoordinatorInfoDto();
        dto.setIdCoordinador(coordinatorInfo.getIdCoordinador());
        dto.setOficinaAtencion(coordinatorInfo.getOficinaAtencion());
        dto.setHorarioAtencion(coordinatorInfo.getHorarioAtencion());
        dto.setExtensionTelefonica(coordinatorInfo.getExtensionTelefonica());
        dto.setFechaNombramiento(coordinatorInfo.getFechaNombramiento());
        dto.setFechaFinPeriodo(coordinatorInfo.getFechaFinPeriodo());
        dto.setEstado(coordinatorInfo.getEstado());
        dto.setObservaciones(coordinatorInfo.getObservaciones());
        if (coordinatorInfo.getUser() != null) {
            dto.setIdUsuario(coordinatorInfo.getUser().getIdUsuario());
        }
        if (coordinatorInfo.getCareer() != null) {
            dto.setIdCarrera(coordinatorInfo.getCareer().getIdCarrera());
        }
        return dto;
    }
}

