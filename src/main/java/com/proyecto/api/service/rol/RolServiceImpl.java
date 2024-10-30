package com.proyecto.api.service.rol;

import com.proyecto.api.modelo.Rol;
import com.proyecto.api.repository.RolRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RolServiceImpl implements RolService{

    private final RolRepository repository;

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public Rol createRole(Rol role) {
        return repository.create(role);
    }

    @Override
    public List<Rol> getRoles() {
        return repository.getRol();
    }

    @Override
    public Rol findRoleById(String idRole) {
        if(profile.equals("postgres")) {
            try {
                Long id = Long.parseLong(idRole);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid idBook format for Postgres: " + idRole);
            }
        }
        return repository.findRoleById(idRole)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + idRole));
    }

    @Override
    public Rol updateRole(String idRole, Rol role) {
        if(profile.equals("postgres")) {
            try {
                Long id = Long.parseLong(idRole);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid idBook format for Postgres: " + idRole);
            }
        }
        Rol roleFound = repository.findRoleById(idRole)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + idRole));
        roleFound.setRol(role.getRol());
        return repository.updateRol(roleFound);
    }

    @Override
    public void deleteRole(String idRole) {
        if(profile.equals("postgres")) {
            try {
                Long id = Long.parseLong(idRole);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid idBook format for Postgres: " + idRole);
            }
        }
        repository.findRoleById(idRole)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id " + idRole));
        repository.deleteRol(idRole);
    }
}
