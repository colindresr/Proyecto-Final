package com.proyecto.api.security.jwt;

import com.proyecto.api.dto.UserRepositoryDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class OperationJwtImpl implements OperationJwt{

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Override
    public String generateToken(UserRepositoryDto user, Calendar calendar){
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("User", user.getName())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
