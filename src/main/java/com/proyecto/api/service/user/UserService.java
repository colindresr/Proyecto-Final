package com.proyecto.api.service.user;

import com.proyecto.api.dto.RequestUpdate;
import com.proyecto.api.dto.ResponseUser;

import java.util.List;

public interface UserService {

    List<ResponseUser> getUsers();

    ResponseUser findUserById(String idUser);

    ResponseUser updateUser(String idUser, RequestUpdate userRequestUpdate);

    void deleteUser(String idUser);
}
