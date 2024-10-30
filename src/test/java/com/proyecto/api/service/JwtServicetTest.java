package com.proyecto.api.service;

import com.proyecto.api.service.auth.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Field;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtServiceTest {
    private JwtService jwtService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtService();


        setPrivateField(jwtService, "secretKey", "fa32693d894e134bd48093361c49702b3f2f51042203a332ac7fe9d65d7f285e");
        setPrivateField(jwtService, "jwtExpiration", 3600000); // 1 hora
    }

    private void setPrivateField(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    @Test
    void testGenerateToken() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.generateToken(userDetails, Collections.singletonList("ROLE_USER"));

        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void testExtractUsername() {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = jwtService.generateToken(userDetails, Collections.singletonList("ROLE_USER"));

        String username = jwtService.extractUsername(token);

        assertEquals("testUser", username);
    }


    @Test
    void testIsTokenValid_ValidToken() {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = jwtService.generateToken(userDetails, Collections.singletonList("ROLE_USER"));

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }


    @Test
    void testExtractAllClaims() throws Exception {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = jwtService.generateToken(userDetails, Collections.singletonList("ROLE_USER"));


        Claims claims = invokeExtractAllClaims(token);

        assertNotNull(claims);
        assertEquals("testUser", claims.getSubject());
        assertNotNull(claims.getExpiration());
    }


    private Claims invokeExtractAllClaims(String token) throws Exception {
        var method = jwtService.getClass().getDeclaredMethod("extractAllClaims", String.class);
        method.setAccessible(true);
        return (Claims) method.invoke(jwtService, token);
    }

    @Test
    void testIsTokenExpired() throws Exception {
        String token = jwtService.generateToken(userDetails, Collections.singletonList("ROLE_USER"));

        // Usamos reflexión para acceder al método privado
        boolean isExpired = invokeIsTokenExpired(token);

        assertFalse(isExpired);
    }

    private boolean invokeIsTokenExpired(String token) throws Exception {
        var method = jwtService.getClass().getDeclaredMethod("isTokenExpired", String.class);
        method.setAccessible(true);
        return (boolean) method.invoke(jwtService, token);
    }
}