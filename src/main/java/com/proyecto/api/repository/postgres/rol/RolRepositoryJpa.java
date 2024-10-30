package com.proyecto.api.repository.postgres.rol;

import com.proyecto.api.modelo.Rol;
import com.proyecto.api.modelo.sql.RolSql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositoryJpa extends JpaRepository<RolSql, Long> {
    Optional<RolSql> findByRol(String rol);

}
