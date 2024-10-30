package com.proyecto.api.service.rol;

import com.proyecto.api.modelo.Rol;

import java.util.List;

public interface RolService {

    Rol createRole(Rol role);
    List<Rol> getRoles();
    Rol findRoleById(String idRole);
    Rol updateRole(String idRole, Rol role);
    void deleteRole(String idRole);
}
