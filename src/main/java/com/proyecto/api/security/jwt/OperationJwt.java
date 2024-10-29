package com.proyecto.api.security.jwt;

import java.util.Calendar;

public interface OperationJwt {

    String generateToken(UserRepositoryDto user, Calendar calendar);
}
