package com.proyecto.api.util;

import com.proyecto.api.dto.ResponseUser;
import com.proyecto.api.modelo.Rol;
import com.proyecto.api.modelo.User;
import com.proyecto.api.modelo.mongo.UserMongo;
import com.proyecto.api.modelo.sql.RolSql;
import com.proyecto.api.modelo.sql.UserSql;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserUtil {

    private final RolUtil rolUtil;

    public UserSql userToUserPostgres(User user) {
        UserSql userPostgres = new UserSql();
        userPostgres.setId((user.getId() != null && !user.getId().isEmpty())
                ? Long.parseLong(user.getId()) : null);
        userPostgres.setName(user.getName());
        userPostgres.setUsername(user.getUsername());
        userPostgres.setEmail(user.getEmail());
        userPostgres.setPassword(user.getPassword());
        userPostgres.setDateCreation(user.getDateCreation());
        userPostgres.setDateUpdate(user.getDateUpdate());
        Set<RolSql> rolSql = user.getRoles().stream()
                .map(rolUtil::rolToRolSql)
                .collect(Collectors.toSet());
        userPostgres.setRoles(rolSql);
        return userPostgres;
    }

    public User userPostgresToUser(UserSql userSql) {
        User user = new User();
        user.setId(String.valueOf(userSql.getId()));
        user.setName(userSql.getName());
        user.setUsername(userSql.getUsername());
        user.setEmail(userSql.getEmail());
        user.setPassword(userSql.getPassword());
        user.setDateCreation(userSql.getDateCreation());
        user.setDateUpdate(userSql.getDateUpdate());
        Set<Rol> roles = userSql.getRoles().stream()
                .map(rolUtil::rolPostgresToRol)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return user;
    }

    public User userMongoToUser(UserMongo userMongo) {
        User user = new User();
        user.setId(userMongo.getId());
        user.setName(userMongo.getName());
        user.setUsername(userMongo.getUsername());
        user.setEmail(userMongo.getEmail());
        user.setPassword(userMongo.getPassword());
        user.setDateCreation(userMongo.getDateCreation());
        user.setDateUpdate(userMongo.getDateUpdate());
        Set<Rol> roles = userMongo.getRoles().stream()
                .map(rolUtil::rolMongoToRol)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return user;
    }

    public ResponseUser userToUserResponse(User user) {
        ResponseUser userResponse = new ResponseUser();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setDateCreation(user.getDateCreation());
        userResponse.setDateUpdate(user.getDateUpdate());
        return userResponse;
    }


}
