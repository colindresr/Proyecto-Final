package com.proyecto.api.dto;


import com.proyecto.api.modelo.mongo.UserMongo;
import com.proyecto.api.modelo.sql.User;

import java.io.Serializable;

public class UserCreateDto implements Serializable {
    private static final long serialVersionUID= 1L;

    private String id;
    private String name;
    private String email;
    private String password;

    public UserCreateDto(User user) {
        this.id = String.valueOf(user.getId());
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserCreateDto(UserMongo user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserCreateDto(UserRepositoryDto user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    // ----------------------------------------------------------------  Getters & Setters ----------------------------------------------------------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ----------------------------------------------------------------  toString() ----------------------------------------------------------------

    @Override
    public String toString() {
        return "UserCreateDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
