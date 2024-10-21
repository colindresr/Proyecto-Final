package com.proyecto.api.dto.authentication;

import java.util.Date;

public class TokenDto {
    private String jwt;
    private Date expirationDateJwt;

    public TokenDto() {
    }

    public TokenDto(String jwt, Date expirationDateJwt) {
        this.jwt = jwt;
        this.expirationDateJwt = expirationDateJwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Date getExpirationDateJwt() {
        return expirationDateJwt;
    }

    public void setExpirationDateJwt(Date expirationDateJwt) {
        this.expirationDateJwt = expirationDateJwt;
    }
}
