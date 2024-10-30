package com.proyecto.api.repository.postgres.rol;

import com.proyecto.api.modelo.Rol;
import com.proyecto.api.modelo.sql.RolSql;
import com.proyecto.api.repository.RolRepository;
import com.proyecto.api.util.RolUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("sql")
@RequiredArgsConstructor
@Repository
public class RolRepositorySql implements RolRepository {

    private final RolRepositoryJpa rolRepositoryJpa;
    private final RolUtil rolUtil;

    @Override
    public Rol create(Rol rol) {
        RolSql rolePostgres = rolUtil.rolToRolSql(rol);
        RolSql newRole = rolRepositoryJpa.save(rolePostgres);
        return rolUtil.rolPostgresToRol(newRole);
    }

    @Override
    public List<Rol> getRol() {
        return rolRepositoryJpa.findAll().stream()
                .map(rolUtil::rolPostgresToRol)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Rol> findRoleById(String idRole) {
        Optional<RolSql> rolePostgres = rolRepositoryJpa.findById(Long.parseLong(idRole));
        return rolePostgres.map(rolUtil::rolPostgresToRol);
    }

    @Override
    public Rol updateRol(Rol role) {
        RolSql rolePostgres = rolUtil.rolToRolSql(role);
        RolSql newRole = rolRepositoryJpa.save(rolePostgres);
        return rolUtil.rolPostgresToRol(newRole);
    }

    @Override
    public void deleteRol(String idRole) {
        rolRepositoryJpa.deleteById(Long.parseLong(idRole));
    }

    @Override
    public Optional<Rol> findRolByName(String roleName) {
        Optional<RolSql> rolePostgres = rolRepositoryJpa.findByRol(roleName);
        return rolePostgres.map(rolUtil::rolPostgresToRol);
    }
}
