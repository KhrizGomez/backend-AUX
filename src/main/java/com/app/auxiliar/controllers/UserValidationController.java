package com.app.auxiliar.controllers;

import com.app.auxiliar.dtos.UserValidationResponseDto;
import com.app.auxiliar.services.IUserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validation")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserValidationController {

    private final IUserValidationService userValidationService;

    /**
     * Valida un usuario por su número de cédula.
     * Verifica que:
     * - El usuario existe
     * - El usuario está activo
     * - Según el rol (estudiante/coordinador/decano) verifica que esté activo en su función
     *
     * Para estudiantes: verifica que esté matriculado
     * Para coordinadores: verifica que esté activo como coordinador
     * Para decanos: verifica que esté activo como decano
     *
     * @param cedula Número de cédula del usuario
     * @return Información completa del usuario según su rol
     */
    @GetMapping("/user/{cedula}")
    public ResponseEntity<UserValidationResponseDto> validateUserByCedula(@PathVariable String cedula) {
        UserValidationResponseDto response = userValidationService.validateUserByCedula(cedula);
        return ResponseEntity.ok(response);
    }
}

