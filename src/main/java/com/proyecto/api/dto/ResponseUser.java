package com.proyecto.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseUser {
    private String id;

    private String name;

    private String username;

    private String email;

    private LocalDateTime dateCreation;

    private LocalDateTime dateUpdate;
}
