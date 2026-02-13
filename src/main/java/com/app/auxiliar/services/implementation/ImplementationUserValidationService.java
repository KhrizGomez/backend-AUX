package com.app.auxiliar.services.implementation;

import com.app.auxiliar.dtos.UserValidationResponseDto;
import com.app.auxiliar.entities.*;
import com.app.auxiliar.repositories.*;
import com.app.auxiliar.services.IUserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImplementationUserValidationService implements IUserValidationService {

    private final IUserRepository userRepository;
    private final IStudentInfoRepository studentInfoRepository;
    private final ICoordinatorInfoRepository coordinatorInfoRepository;
    private final IDeanInfoRepository deanInfoRepository;

    @Override
    @Transactional(readOnly = true)
    public UserValidationResponseDto validateUserByCedula(String cedula) {
        // 1. Buscar usuario por cédula
        User user = userRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("No se encontró usuario con la cédula: " + cedula));

        // 2. Verificar que el usuario esté activo
        if (user.getEstado() == null || !user.getEstado()) {
            throw new RuntimeException("El usuario con cédula " + cedula + " no está activo en el sistema");
        }

        // 3. Construir respuesta según el rol
        UserValidationResponseDto response = new UserValidationResponseDto();

        // Información básica del usuario
        setUserInfo(response, user);

        // Cargar información específica según el rol
        String rol = user.getRol();
        if (rol != null) {
            switch (rol.toLowerCase()) {
                case "estudiante":
                    loadStudentInfo(response, user);
                    break;
                case "coordinador":
                    loadCoordinatorInfo(response, user);
                    break;
                case "decano":
                    loadDeanInfo(response, user);
                    break;
                default:
                    throw new RuntimeException("Rol no reconocido: " + rol);
            }
        }

        return response;
    }

    private void setUserInfo(UserValidationResponseDto response, User user) {
        response.setIdUsuario(user.getIdUsuario());
        response.setCedula(user.getCedula());
        response.setNombres(user.getNombres());
        response.setApellidos(user.getApellidos());
        response.setCorreoPersonal(user.getCorreoPersonal());
        response.setCorreoInstitucional(user.getCorreoInstitucional());
        response.setTelefono(user.getTelefono());
        response.setFechaNacimiento(user.getFechaNacimiento());
        response.setGenero(user.getGenero());
        response.setDireccion(user.getDireccion());
        response.setRol(user.getRol());
        response.setEstadoUsuario(user.getEstado());
    }

    private void loadStudentInfo(UserValidationResponseDto response, User user) {
        StudentInfo studentInfo = studentInfoRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("No se encontró información de estudiante para la cédula: " + user.getCedula()));

        // Verificar que esté matriculado
        if (studentInfo.getMatriculado() == null || !studentInfo.getMatriculado()) {
            throw new RuntimeException("El estudiante con cédula " + user.getCedula() + " no está matriculado actualmente");
        }

        // Información del estudiante
        response.setIdEstudiante(studentInfo.getIdEstudiante());
        response.setNumeroMatricula(studentInfo.getNumeroMatricula());
        response.setParalelo(studentInfo.getParalelo());
        response.setJornada(studentInfo.getJornada());
        response.setFechaIngreso(studentInfo.getFechaIngreso());
        response.setFechaEgreso(studentInfo.getFechaEgreso());
        response.setEstadoAcademico(studentInfo.getEstadoAcademico());
        response.setPromedioGeneral(studentInfo.getPromedioGeneral());
        response.setCreditosAprobados(studentInfo.getCreditosAprobados());
        response.setCreditosTotales(studentInfo.getCreditosTotales());
        response.setMatriculado(studentInfo.getMatriculado());
        response.setEsBecado(studentInfo.getEsBecado());
        response.setTipoBeca(studentInfo.getTipoBeca());

        // Información de la carrera
        Career career = studentInfo.getCareer();
        if (career != null) {
            setCareerInfo(response, career);

            // Información de la facultad
            if (career.getFaculty() != null) {
                setFacultyInfo(response, career.getFaculty());
            }
        }

        // Información del semestre actual
        Semester currentSemester = studentInfo.getCurrentSemester();
        if (currentSemester != null) {
            response.setIdSemestre(currentSemester.getIdSemestre());
            response.setCodigoPeriodo(currentSemester.getCodigoPeriodo());
            response.setNombrePeriodo(currentSemester.getNombrePeriodo());
            response.setFechaInicioPeriodo(currentSemester.getFechaInicio());
            response.setFechaFinPeriodo(currentSemester.getFechaFin());
            response.setEsPeriodoActual(currentSemester.getEsPeriodoActual());
        }
    }

    private void loadCoordinatorInfo(UserValidationResponseDto response, User user) {
        CoordinatorInfo coordinatorInfo = coordinatorInfoRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("No se encontró información de coordinador para la cédula: " + user.getCedula()));

        // Verificar que esté activo como coordinador
        if (coordinatorInfo.getEstado() == null || !coordinatorInfo.getEstado()) {
            throw new RuntimeException("El coordinador con cédula " + user.getCedula() + " no está activo actualmente");
        }

        // Información del coordinador
        response.setIdCoordinador(coordinatorInfo.getIdCoordinador());
        response.setOficinaAtencion(coordinatorInfo.getOficinaAtencion());
        response.setHorarioAtencion(coordinatorInfo.getHorarioAtencion());
        response.setExtensionTelefonica(coordinatorInfo.getExtensionTelefonica());
        response.setFechaNombramientoCoordinador(coordinatorInfo.getFechaNombramiento());
        response.setFechaFinPeriodoCoordinador(coordinatorInfo.getFechaFinPeriodo());

        // Información de la carrera
        Career career = coordinatorInfo.getCareer();
        if (career != null) {
            setCareerInfo(response, career);

            // Información de la facultad
            if (career.getFaculty() != null) {
                setFacultyInfo(response, career.getFaculty());
            }
        }
    }

    private void loadDeanInfo(UserValidationResponseDto response, User user) {
        DeanInfo deanInfo = deanInfoRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("No se encontró información de decano para la cédula: " + user.getCedula()));

        // Verificar que esté activo como decano
        if (deanInfo.getEstado() == null || !deanInfo.getEstado()) {
            throw new RuntimeException("El decano con cédula " + user.getCedula() + " no está activo actualmente");
        }

        // Información del decano
        response.setIdDecano(deanInfo.getIdDecano());
        response.setFechaNombramientoDecano(deanInfo.getFechaNombramiento());
        response.setFechaFinPeriodoDecano(deanInfo.getFechaFinPeriodo());
        response.setResolucionNombramiento(deanInfo.getResolucionNombramiento());

        // Información de la facultad
        Faculty faculty = deanInfo.getFaculty();
        if (faculty != null) {
            setFacultyInfo(response, faculty);
        }
    }

    private void setCareerInfo(UserValidationResponseDto response, Career career) {
        response.setIdCarrera(career.getIdCarrera());
        response.setNombreCarrera(career.getNombreCarrera());
        response.setCodigoCarrera(career.getCodigoCarrera());
        response.setDuracionSemestres(career.getDuracionSemestres());
        response.setModalidad(career.getModalidad());
        response.setTituloOtorga(career.getTituloOtorga());
    }

    private void setFacultyInfo(UserValidationResponseDto response, Faculty faculty) {
        response.setIdFacultad(faculty.getIdFacultad());
        response.setNombreFacultad(faculty.getNombreFacultad());
        response.setCodigoFacultad(faculty.getCodigoFacultad());
        response.setUbicacionOficinaFacultad(faculty.getUbicacionOficina());
        response.setEmailFacultad(faculty.getEmailFacultad());
    }
}

