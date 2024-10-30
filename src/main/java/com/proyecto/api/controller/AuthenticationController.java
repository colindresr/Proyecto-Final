package com.proyecto.api.controller;

import com.proyecto.api.dto.RequestLogin;
import com.proyecto.api.dto.RequestUser;
import com.proyecto.api.dto.ResponseLogin;
import com.proyecto.api.modelo.User;
import com.proyecto.api.service.auth.AuthenticationService;
import com.proyecto.api.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RequestUser registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> authenticate(@RequestBody RequestLogin loginUserDto) {
        User authenticatedUser = authenticationService.login(loginUserDto);
        List<String> roles = authenticationService.getRolesName(authenticatedUser);
        String jwtToken = jwtService.generateToken(authenticatedUser, roles);

        ResponseLogin loginResponse = new ResponseLogin();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpireIn(jwtService.getExpirationTime());

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}