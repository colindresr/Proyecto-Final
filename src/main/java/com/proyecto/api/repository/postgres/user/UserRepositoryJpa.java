package com.proyecto.api.repository.postgres.user;

import com.proyecto.api.modelo.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
