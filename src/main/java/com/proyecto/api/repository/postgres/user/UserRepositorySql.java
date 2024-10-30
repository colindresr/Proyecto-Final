package com.proyecto.api.repository.postgres.user;

import com.proyecto.api.modelo.User;
import com.proyecto.api.modelo.sql.UserSql;
import com.proyecto.api.repository.UserRepository;
import com.proyecto.api.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("sql")
@Repository
@RequiredArgsConstructor
public class UserRepositorySql implements UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;
    private final UserUtil userUtil;

    @Override
    public User createUser(User user) {
        UserSql userPostgres = userUtil.userToUserPostgres(user);
        UserSql newUser = userRepositoryJpa.save(userPostgres);
        return userUtil.userPostgresToUser(newUser);
    }


    @Override
    public List<User> getUsers() {
        return userRepositoryJpa.findAll().stream()
                .map(userUtil::userPostgresToUser)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findUserById(String id) {
        Optional<UserSql> userPostgres = userRepositoryJpa.findById(Long.parseLong(id));
        return userPostgres.map(userUtil::userPostgresToUser);
    }


    @Override
    public User updateUser(User user) {
        UserSql userPostgres = userUtil.userToUserPostgres(user);
        UserSql newUser = userRepositoryJpa.save(userPostgres);
        return userUtil.userPostgresToUser(newUser);
    }

    @Override
    public void deleteUser(String idUser) {
        userRepositoryJpa.deleteById(Long.parseLong(idUser));
    }


    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<UserSql> userPostgres = userRepositoryJpa.findByEmail(email);
        return userPostgres.map(userUtil::userPostgresToUser);
    }


    @Override
    public Optional<User> findUserByUsername(String username) {
        Optional<UserSql> userPostgres = userRepositoryJpa.findByUsername(username);
        return userPostgres.map(userUtil::userPostgresToUser);
    }

}



