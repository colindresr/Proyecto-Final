package com.proyecto.api.util;

import com.proyecto.api.modelo.Rol;
import com.proyecto.api.modelo.mongo.RolMongo;
import com.proyecto.api.modelo.sql.RolSql;
import org.springframework.stereotype.Component;

@Component
public class RolUtil {

    public RolSql rolToRolSql(Rol rol){
        RolSql rolSql = new RolSql();
        rolSql.setIdRol((rol.getIdRol() != null && !rol.getIdRol().isEmpty())
                ? Long.parseLong(rol.getIdRol()) : null);
        rolSql.setRol(rol.getRol());
        return rolSql;
    }

    public Rol rolPostgresToRol(RolSql rolSql) {
        Rol rol = new Rol();
        rol.setIdRol(rolSql.getIdRol().toString());
        rol.setRol(rolSql.getRol());
        return rol;
    }

    public RolMongo rolToRoleMongo(Rol rol) {
        RolMongo rolMongo = new RolMongo();
        rolMongo.setId(rol.getIdRol());
        rolMongo.setRol(rol.getRol());
        return rolMongo;
    }

    public Rol rolMongoToRol(RolMongo rolMongo) {
        Rol rol = new Rol();
        rol.setIdRol(rolMongo.getId());
        rol.setRol(rolMongo.getRol());
        return rol;
    }
}
