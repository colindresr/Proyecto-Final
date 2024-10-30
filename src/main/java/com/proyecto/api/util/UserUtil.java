package com.proyecto.api.util;

import com.proyecto.api.dto.ResponseUser;
import com.proyecto.api.modelo.Rol;
import com.proyecto.api.modelo.User;
import com.proyecto.api.modelo.mongo.RolMongo;
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

    public User userPostgresToUser(UserSql userPostgres) {
        User user = new User();
        user.setId(String.valueOf(userPostgres.getId()));
        user.setName(userPostgres.getName());
        user.setUsername(userPostgres.getUsername());
        user.setEmail(userPostgres.getEmail());
        user.setPassword(userPostgres.getPassword());
        user.setDateCreation(userPostgres.getDateCreation());
        user.setDateUpdate(userPostgres.getDateUpdate());

        Set<Rol> roles = userPostgres.getRoles().stream()
                .map(rolUtil::rolPostgresToRol)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return user;
    }

    public UserMongo userToUserMongo(User user) {
        UserMongo userMongo = new UserMongo();
        userMongo.setId(user.getId());
        userMongo.setName(user.getName());
        userMongo.setUsername(user.getUsername());
        userMongo.setEmail(user.getEmail());
        userMongo.setPassword(user.getPassword());
        userMongo.setDateCreation(user.getDateCreation());
        userMongo.setDateUpdate(user.getDateUpdate());
        Set<RolMongo> roles = user.getRoles().stream()
                .map(rolUtil::rolToRoleMongo)
                .collect(Collectors.toSet());
        userMongo.setRoles(roles);
        return userMongo;
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
