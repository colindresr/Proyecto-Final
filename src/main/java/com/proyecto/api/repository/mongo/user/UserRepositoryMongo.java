package com.proyecto.api.repository.mongo.user;

import com.proyecto.api.modelo.mongo.UserMongo;
import com.proyecto.api.modelo.sql.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepositoryMongo extends MongoRepository<UserMongo, String> {

    Optional<UserMongo> findByEmail(String email);
}
