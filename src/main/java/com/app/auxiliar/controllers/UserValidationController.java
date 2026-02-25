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
    
    @GetMapping("/user/{cedula}")
    public ResponseEntity<UserValidationResponseDto> validateUserByCedula(@PathVariable String cedula) {
        UserValidationResponseDto response = userValidationService.validateUserByCedula(cedula);
        return ResponseEntity.ok(response);
    }
}

