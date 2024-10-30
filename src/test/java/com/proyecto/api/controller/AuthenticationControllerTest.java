package com.proyecto.api.controller;

import com.proyecto.api.dto.RequestLogin;
import com.proyecto.api.dto.RequestUser;
import com.proyecto.api.dto.ResponseLogin;
import com.proyecto.api.modelo.User;
import com.proyecto.api.service.auth.AuthenticationService;
import com.proyecto.api.service.auth.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class AuthenticationControllerTest {

    private AuthenticationService authenticationService;
    private JwtService jwtService;
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        authenticationService = Mockito.mock(AuthenticationService.class);
        jwtService = Mockito.mock(JwtService.class);
        authenticationController = new AuthenticationController(jwtService, authenticationService);
    }

    @Test
    void testRegister() {
        RequestUser registerUserDto = new RequestUser();
        registerUserDto.setUsername("testUser");
        registerUserDto.setPassword("testPassword");

        // Crear un usuario simulado
        User registeredUser = new User();
        registeredUser.setUsername("testUser");


        when(authenticationService.signup(registerUserDto)).thenReturn(registeredUser);

        ResponseEntity<User> response = authenticationController.register(registerUserDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(registeredUser, response.getBody());
    }

    @Test
    void testAuthenticate() {
        RequestLogin loginUserDto = new RequestLogin();
        loginUserDto.setUsername("testUser");
        loginUserDto.setPassword("testPassword");

        User authenticatedUser = new User();
        authenticatedUser.setUsername("testUser");

        when(authenticationService.login(loginUserDto)).thenReturn(authenticatedUser);
        when(authenticationService.getRolesName(authenticatedUser)).thenReturn(Arrays.asList("USER_ROLE"));
        when(jwtService.generateToken(authenticatedUser, Arrays.asList("USER_ROLE"))).thenReturn("test.jwt.token");
        when(jwtService.getExpirationTime()).thenReturn(3600L);

        ResponseEntity<ResponseLogin> response = authenticationController.authenticate(loginUserDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("test.jwt.token", response.getBody().getToken());
        assertEquals(3600L, response.getBody().getExpireIn());
    }
}
