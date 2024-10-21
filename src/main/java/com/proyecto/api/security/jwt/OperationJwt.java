package com.proyecto.api.security.jwt;

import com.proyecto.api.dto.UserRepositoryDto;

import java.util.Calendar;

public interface OperationJwt {

    String generateToken(UserRepositoryDto user, Calendar calendar);
}
