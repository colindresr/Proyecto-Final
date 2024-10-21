package com.proyecto.api.service.user;

import com.proyecto.api.dto.UserCreateDto;
import com.proyecto.api.dto.UserRegisterDto;
import com.proyecto.api.dto.UserRepositoryDto;

import java.util.List;

public interface UserService {

    List<UserCreateDto> getAllUsers();
    UserCreateDto findUserById(String idUser);
    UserCreateDto createUser(UserRegisterDto user);
    Boolean updateUser(String idUser, UserRegisterDto user);
    Boolean deleteUser(String idUser);
    UserRepositoryDto findByEmail(String email);
}
