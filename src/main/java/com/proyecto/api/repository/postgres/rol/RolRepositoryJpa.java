package com.proyecto.api.repository.postgres.rol;

import com.proyecto.api.modelo.sql.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositoryJpa extends JpaRepository<Rol, Long> {
}
