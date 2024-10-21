package com.proyecto.api.dto.mapper;

import com.proyecto.api.dto.UserRepositoryDto;
import com.proyecto.api.modelo.mongo.UserMongo;
import com.proyecto.api.modelo.sql.User;
import com.proyecto.api.modelo.EnumRol;

import java.util.List;
import java.util.stream.Collectors;

public class DataMapper {
    public static User convertUserMongoToUser(UserMongo userMongo){
        return new User(
                userMongo.getName(),
                userMongo.getPassword(),
                userMongo.getEmail()
        );
    }

    public static UserMongo convertUserToUserMongo(User user){
        return new UserMongo(
                user.getName(),
                user.getPassword(),
                user.getEmail()
        );
    }

    public static UserRepositoryDto convertUserToUserRepositoryDto(User user){

        List<EnumRol> roleEnums = user.getRoles().stream()
                .map(rol -> EnumRol.valueOf(rol.getRol()))  // Asegúrate de que el método getRol() retorne el nombre correcto del rol
                .collect(Collectors.toList());

        return new UserRepositoryDto(
                String.valueOf(user.getId()),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                roleEnums
        );
    }

    public static UserRepositoryDto convertUserMongoToUserRepositoryDto(UserMongo userMongo){
        return new UserRepositoryDto(
                userMongo.getId(),
                userMongo.getName(),
                userMongo.getPassword(),
                userMongo.getEmail(),
                userMongo.getRoles()
        );
    }

    /*
    public static PurchaseRepositoryDto convertPurchaseToPurchaseRepositoryDto(Purchase purchase){
        return new PurchaseRepositoryDto(
                String.valueOf(purchase.getIdPurchase()),
                String.valueOf(purchase.getIdUser()),
                purchase.getDatePurchase(),
                purchase.getTotalPurchase()
        );
    }
    */

}
