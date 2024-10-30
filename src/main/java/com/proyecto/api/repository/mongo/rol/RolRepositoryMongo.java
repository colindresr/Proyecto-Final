package com.proyecto.api.repository.mongo.rol;

import com.proyecto.api.modelo.Rol;
import com.proyecto.api.modelo.mongo.RolMongo;
import com.proyecto.api.repository.RolRepository;
import com.proyecto.api.util.RolUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongo")
@RequiredArgsConstructor
@Repository
public class RolRepositoryMongo implements RolRepository {

    private final RolRepositoryJpaMongo mongo;
    private final RolUtil rolUtil;

    @Override
    public Rol create(Rol role) {
        RolMongo roleMongo = rolUtil.rolToRoleMongo(role);
        RolMongo newMongo = mongo.save(roleMongo);
        return rolUtil.rolMongoToRol(newMongo);
    }


    @Override
    public List<Rol> getRol() {
        return mongo.findAll().stream()
                .map(rolUtil::rolMongoToRol)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Rol> findRoleById(String idRole) {
        Optional<RolMongo> roleMongo = mongo.findById(idRole);
        return roleMongo.map(rolUtil::rolMongoToRol);
    }

    @Override
    public Rol updateRol(Rol role) {
        RolMongo roleMongo = rolUtil.rolToRoleMongo(role);
        RolMongo newMongo = mongo.save(roleMongo);
        return rolUtil.rolMongoToRol(newMongo);
    }

    @Override
    public void deleteRol(String idRole) {
        mongo.deleteById(idRole);
    }

    @Override
    public Optional<Rol> findRolByName(String roleName) {
        Optional<RolMongo> roleMongo = mongo.findByRol(roleName);
        return roleMongo.map(rolUtil::rolMongoToRol);
    }
}
