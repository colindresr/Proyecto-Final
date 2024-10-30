package com.proyecto.api.repository;

import com.proyecto.api.modelo.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User createUser(User user);
    List<User> getUsers();
    Optional<User> findUserById(String id);
    User updateUser(User user);
    void deleteUser(String id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);

}
