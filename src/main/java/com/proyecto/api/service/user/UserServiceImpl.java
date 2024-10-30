package com.proyecto.api.service.user;


import com.proyecto.api.dto.RequestUpdate;
import com.proyecto.api.dto.ResponseUser;
import com.proyecto.api.modelo.User;
import com.proyecto.api.repository.UserRepository;
import com.proyecto.api.util.UserUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;

    private static final String USER_NOT_FOUND = "User not found with ID: ";

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public List<ResponseUser> getUsers() {
        List<User> users = userRepository.getUsers();
        return users.stream().map(userUtil::userToUserResponse).collect(Collectors.toList());
    }

    @Override
    public ResponseUser findUserById(String idUser) {
        if(profile.equals("postgres")) {
            try {
                Long id = Long.parseLong(idUser);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid idBook format for Postgres: " + idUser);
            }
        }
        User user = userRepository.findUserById(idUser)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + idUser));
        return userUtil.userToUserResponse(user);
    }

    @Override
    public ResponseUser updateUser(String idUser, RequestUpdate userRequestUpdate) {
        if(profile.equals("postgres")) {
            try {
                Long id = Long.parseLong(idUser);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid idBook format for Postgres: " + idUser);
            }
        }
        User user = userRepository.findUserById(idUser)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + idUser));
        user.setName(userRequestUpdate.getName());
        user.setUsername(userRequestUpdate.getUsername());
        user.setEmail(userRequestUpdate.getEmail());
        User update = userRepository.updateUser(user);
        return userUtil.userToUserResponse(update);
    }

    @Override
    public void deleteUser(String idUser) {
        if(profile.equals("postgres")) {
            try {
                Long id = Long.parseLong(idUser);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid idBook format for Postgres: " + idUser);
            }
        }
        userRepository.findUserById(idUser)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + idUser));
        userRepository.deleteUser(idUser);
    }

}
