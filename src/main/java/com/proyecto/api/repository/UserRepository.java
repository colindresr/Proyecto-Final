package com.proyecto.api.repository;

import com.proyecto.api.dto.UserRepositoryDto;

import java.util.List;

public interface UserRepository {

    List<UserRepositoryDto> getAllUsers();
    UserRepositoryDto findUserById(String idUser);
    UserRepositoryDto createUser(UserRepositoryDto user);
    Boolean updateUser(String idUser, UserRepositoryDto user);
    Boolean deleteUser(String idUser);
    UserRepositoryDto findByEmail(String email);
}
