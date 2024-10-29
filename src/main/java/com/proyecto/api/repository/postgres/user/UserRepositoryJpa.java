package com.proyecto.api.repository.postgres.user;

import com.proyecto.api.modelo.sql.UserSql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UserSql, Long> {

    Optional<UserSql> findByEmail(String email);
    Optional<UserSql> findByUsername(String username);
}
