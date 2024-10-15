package com.proyecto.api.dto;


import com.proyecto.api.modelo.sql.User;

import java.io.Serializable;

public class UserCreate implements Serializable {
    private static final long serialVersionUID= 1L;

    private String id;
    private String name;
    private String email;
    private String password;

    public UserCreate(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
