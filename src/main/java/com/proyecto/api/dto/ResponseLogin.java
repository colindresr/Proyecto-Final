package com.proyecto.api.dto;

import lombok.Data;

@Data
public class ResponseLogin {
    private String token;
    private long expireIn;

}
