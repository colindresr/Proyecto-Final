package com.proyecto.api.repository.mongo.user;

import com.proyecto.api.modelo.User;
import com.proyecto.api.modelo.mongo.UserMongo;
import com.proyecto.api.repository.UserRepository;
import com.proyecto.api.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongo")
@RequiredArgsConstructor
@Repository
public class UserRepositoryMongo implements UserRepository {

    private final UserRepositoryJpaMongo mongo;
    private final UserUtil userUtil;

    @Override
    public User createUser(User user) {
        UserMongo userMongo = userUtil.userToUserMongo(user);
        UserMongo newUser = mongo.save(userMongo);
        return userUtil.userMongoToUser(newUser);
    }

    @Override
    public List<User> getUsers() {
        return mongo.findAll().stream()
                .map(userUtil::userMongoToUser)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findUserById(String id) {
        Optional<UserMongo> userMongo = mongo.findById(id);
        return userMongo.map(userUtil::userMongoToUser);
    }

    @Override
    public User updateUser(User user) {
        UserMongo userMongo = userUtil.userToUserMongo(user);
        UserMongo newUser = mongo.save(userMongo);
        return userUtil.userMongoToUser(newUser);
    }

    @Override
    public void deleteUser(String id) {
        mongo.deleteById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<UserMongo> userMongo = mongo.findByEmail(email);
        return userMongo.map(userUtil::userMongoToUser);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        Optional<UserMongo> userMongo = mongo.findByUsername(username); // Buscar en la base de datos
        return userMongo.map(userUtil::userMongoToUser); // Convertir si se encuentra
    }
}
