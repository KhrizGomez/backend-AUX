package com.app.auxiliar.services.implementation;

import com.app.auxiliar.dtos.UserDto;
import com.app.auxiliar.entities.User;
import com.app.auxiliar.repositories.IUserRepository;
import com.app.auxiliar.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImplementationUserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public UserDto showUserById(Integer idUsuario) {
        User user = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + idUsuario));
        return convertToDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    @Override
    public UserDto updateUser(Integer idUsuario, UserDto userDto) {
        User existingUser = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + idUsuario));
        BeanUtils.copyProperties(userDto, existingUser);
        User updatedUser = userRepository.save(existingUser);
        return convertToDto(updatedUser);
    }

    @Override
    public UserDto deleteUser(Integer idUsuario) {
        User existingUser = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + idUsuario));
        userRepository.delete(existingUser);
        return convertToDto(existingUser);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
}

