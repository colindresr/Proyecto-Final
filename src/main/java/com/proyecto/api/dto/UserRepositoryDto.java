package com.proyecto.api.dto;

import com.proyecto.api.modelo.EnumRol;

import java.io.Serializable;
import java.util.List;

public class UserRepositoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private String password;

    private List<EnumRol> roles;

    public UserRepositoryDto(String id, String name, String email, String password, List<EnumRol> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserRepositoryDto(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public List<EnumRol> getRoles() {
        return roles;
    }

    public void setRoles(List<EnumRol> roles) {
        this.roles = roles;
    }

    // ----------------------------------------------------------------  toString() ----------------------------------------------------------------
    @Override
    public String toString() {
        return "UserRepositoryDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
