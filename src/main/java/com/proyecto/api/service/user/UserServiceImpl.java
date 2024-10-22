package com.proyecto.api.service.user;

import com.proyecto.api.dto.UserCreateDto;
import com.proyecto.api.dto.UserRegisterDto;
import com.proyecto.api.dto.UserRepositoryDto;
import com.proyecto.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(@Qualifier("userRepositoryMongoImpl") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserCreateDto> getAllUsers() {
        List<UserCreateDto> userCreatedDtos = new ArrayList<>();
        for (UserRepositoryDto user : userRepository.getAllUsers()){
            userCreatedDtos.add(new UserCreateDto(user));
        }
        return userCreatedDtos;
    }

    @Override
    public UserCreateDto findUserById(String idUser) {
        return new UserCreateDto(userRepository.findUserById(idUser));
    }

    @Override
    public UserCreateDto createUser(UserRegisterDto user) {
        return new UserCreateDto(userRepository.createUser(new UserRepositoryDto(user)));
    }

    @Override
    public Boolean updateUser(String idUser, UserRegisterDto user) {
        return userRepository.updateUser(idUser, new UserRepositoryDto(user));
    }

    @Override
    public Boolean deleteUser(String idUser) {
        return userRepository.deleteUser(idUser);
    }

    @Override
    public UserRepositoryDto findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
