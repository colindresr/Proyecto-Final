package com.proyecto.api.repository;

import com.proyecto.api.modelo.Rol;

import java.util.List;
import java.util.Optional;

public interface RolRepository {

    Rol create(Rol rol);
    List<Rol> getRol();
    Optional<Rol> findRoleById(String idRol);
    Rol updateRol(Rol rol);
    void deleteRol(String idRol);
    Optional<Rol> findRolByName(String rol);
}
