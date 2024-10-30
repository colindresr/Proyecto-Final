package com.proyecto.api.repository.mongo.user;

import com.proyecto.api.modelo.mongo.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepositoryJpaMongo extends MongoRepository<UserMongo, String> {
    Optional<UserMongo> findByUsername(String username);
    Optional<UserMongo> findByEmail(String email);
}
