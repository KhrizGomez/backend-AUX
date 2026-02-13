package com.app.auxiliar.services.implementation;

import com.app.auxiliar.dtos.CareerDto;
import com.app.auxiliar.entities.Career;
import com.app.auxiliar.entities.Faculty;
import com.app.auxiliar.repositories.ICareerRepository;
import com.app.auxiliar.repositories.IFacultyRepository;
import com.app.auxiliar.services.ICareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplementationCareerService implements ICareerService {
    private final ICareerRepository careerRepository;
    private final IFacultyRepository facultyRepository;

    @Override
    public CareerDto showCareerById(Integer idCarrera) {
        Career career = careerRepository.findById(idCarrera)
                .orElseThrow(() -> new RuntimeException("Career not found with id: " + idCarrera));
        return convertToDto(career);
    }

    @Override
    public CareerDto createCareer(CareerDto careerDto) {
        Career career = new Career();
        mapDtoToEntity(careerDto, career);
        Career savedCareer = careerRepository.save(career);
        return convertToDto(savedCareer);
    }

    @Override
    public CareerDto updateCareer(Integer idCarrera, CareerDto careerDto) {
        Career existingCareer = careerRepository.findById(idCarrera)
                .orElseThrow(() -> new RuntimeException("Career not found with id: " + idCarrera));
        mapDtoToEntity(careerDto, existingCareer);
        Career updatedCareer = careerRepository.save(existingCareer);
        return convertToDto(updatedCareer);
    }

    @Override
    public CareerDto deleteCareer(Integer idCarrera) {
        Career existingCareer = careerRepository.findById(idCarrera)
                .orElseThrow(() -> new RuntimeException("Career not found with id: " + idCarrera));
        careerRepository.delete(existingCareer);
        return convertToDto(existingCareer);
    }

    private void mapDtoToEntity(CareerDto dto, Career entity) {
        entity.setNombreCarrera(dto.getNombreCarrera());
        entity.setCodigoCarrera(dto.getCodigoCarrera());
        entity.setDuracionSemestres(dto.getDuracionSemestres());
        entity.setModalidad(dto.getModalidad());
        entity.setTituloOtorga(dto.getTituloOtorga());
        entity.setEstado(dto.getEstado());

        if (dto.getIdFacultad() != null) {
            Faculty faculty = facultyRepository.findById(dto.getIdFacultad())
                    .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + dto.getIdFacultad()));
            entity.setFaculty(faculty);
        } else {
            entity.setFaculty(null);
        }
    }

    private CareerDto convertToDto(Career career) {
        CareerDto dto = new CareerDto();
        dto.setIdCarrera(career.getIdCarrera());
        dto.setNombreCarrera(career.getNombreCarrera());
        dto.setCodigoCarrera(career.getCodigoCarrera());
        dto.setDuracionSemestres(career.getDuracionSemestres());
        dto.setModalidad(career.getModalidad());
        dto.setTituloOtorga(career.getTituloOtorga());
        dto.setEstado(career.getEstado());
        if (career.getFaculty() != null) {
            dto.setIdFacultad(career.getFaculty().getIdFacultad());
        }
        return dto;
    }
}

