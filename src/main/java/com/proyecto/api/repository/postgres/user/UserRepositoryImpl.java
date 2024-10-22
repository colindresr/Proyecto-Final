package com.proyecto.api.repository.postgres.user;

import com.proyecto.api.dto.UserRepositoryDto;
import com.proyecto.api.dto.mapper.DataMapper;
import com.proyecto.api.modelo.sql.Rol;
import com.proyecto.api.modelo.sql.User;
import com.proyecto.api.repository.UserRepository;
import com.proyecto.api.repository.postgres.rol.RolRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Autowired
    private RolRepositoryJpa rolRepositoryJpa;

    @Override
    public List<UserRepositoryDto> getAllUsers() {
        return userRepositoryJpa.findAll().stream()
                .map(user -> DataMapper.convertUserToUserRepositoryDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserRepositoryDto findUserById(String idUser) {
        return DataMapper.convertUserToUserRepositoryDto(userRepositoryJpa.findById(Long.parseLong(idUser)).get());
    }

    @Override
    public UserRepositoryDto createUser(UserRepositoryDto user) {
        Optional<Rol> defaultRole = rolRepositoryJpa.findById(1L);
        User userToCreate = new User(user);
        userToCreate.setRoles(Collections.singletonList(defaultRole.get()));
        return DataMapper.convertUserToUserRepositoryDto(userRepositoryJpa.save(userToCreate));
    }

    @Override
    public Boolean updateUser(String idUser, UserRepositoryDto user) {
        User userFound = userRepositoryJpa.findById(Long.parseLong(idUser)).get();
        if (userFound != null){
            userFound.updateUser(user);
            userRepositoryJpa.save(userFound);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteUser(String idUser) {
        User userFound = userRepositoryJpa.findById(Long.parseLong(idUser)).get();
        if (userFound != null){
            userRepositoryJpa.delete(userFound);
            return true;
        }
        return false;
    }

    @Override
    public UserRepositoryDto findByEmail(String email) {
        Optional<User> userFound = userRepositoryJpa.findByEmail(email);
        if (userFound.isPresent()){
            return DataMapper.convertUserToUserRepositoryDto(userFound.get());
        }else{
            return null;
        }
    }
}
