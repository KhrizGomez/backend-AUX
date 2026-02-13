package com.app.auxiliar.services;

import com.app.auxiliar.dtos.UserDto;

public interface IUserService {
    UserDto showUserById(Integer idUsuario);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Integer idUsuario, UserDto userDto);
    UserDto deleteUser(Integer idUsuario);
}

