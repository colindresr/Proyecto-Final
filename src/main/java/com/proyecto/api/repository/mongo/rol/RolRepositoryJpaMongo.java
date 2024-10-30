package com.proyecto.api.repository.mongo.rol;

import com.proyecto.api.modelo.Rol;
import com.proyecto.api.modelo.mongo.RolMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RolRepositoryJpaMongo extends MongoRepository <RolMongo, String> {

    Optional<RolMongo> findByRole(String role);

}
