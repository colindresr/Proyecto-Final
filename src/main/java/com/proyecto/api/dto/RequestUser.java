package com.proyecto.api.dto;

import lombok.Data;

@Data
public class RequestUser {

    private String name;
    private String username;
    private String password;
    private String email;
}
