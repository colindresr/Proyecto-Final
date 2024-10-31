package com.proyecto.api.service;

import com.proyecto.api.dto.RequestLogin;
import com.proyecto.api.dto.RequestUser;
import com.proyecto.api.modelo.Rol;
import com.proyecto.api.modelo.User;
import com.proyecto.api.repository.RolRepository;
import com.proyecto.api.repository.UserRepository;
import com.proyecto.api.service.auth.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RolRepository roleRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignup_Success() {
        RequestUser userRequest = new RequestUser();
        userRequest.setName("Test User");
        userRequest.setUsername("testuser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");

        Rol role = new Rol();
        role.setRol("ROLE_USER");

        when(passwordEncoder.encode(userRequest.getPassword())).thenReturn("encodedPassword");
        when(roleRepository.findRolByName("ROLE_USER")).thenReturn(Optional.of(role));

        User user = new User();
        user.setRoles(new HashSet<>());
        when(userRepository.createUser(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = authenticationService.signup(userRequest);

        assertNotNull(result);
        assertEquals("Test User", result.getName());
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("encodedPassword", result.getPassword());
        assertEquals(1, result.getRoles().size());
        assertTrue(result.getRoles().contains(role)); // Verificar que el rol está presente
        verify(userRepository, times(1)).createUser(any(User.class));
    }

    @Test
    void testSignup_RoleNotFound() {
        RequestUser userRequest = new RequestUser();
        userRequest.setName("Test User");
        userRequest.setUsername("testuser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");

        when(roleRepository.findRolByName("ROLE_USER")).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            authenticationService.signup(userRequest);
        });

        assertEquals("error creating user, could not assign a role", exception.getMessage());
        verify(userRepository, never()).createUser(any(User.class));
    }

    @Test
    void testLogin_Success() {
        // Arrange
        RequestLogin userRequestLogin = new RequestLogin();
        userRequestLogin.setUsername("testuser");
        userRequestLogin.setPassword("password");

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findUserByUsername(userRequestLogin.getUsername())).thenReturn(Optional.of(user));

        // Act
        User result = authenticationService.login(userRequestLogin);

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepository, times(1)).findUserByUsername(userRequestLogin.getUsername());
    }

    @Test
    void testLogin_UserNotFound() {
        // Arrange
        RequestLogin userRequestLogin = new RequestLogin();
        userRequestLogin.setUsername("testuser");
        userRequestLogin.setPassword("password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findUserByUsername(userRequestLogin.getUsername())).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            authenticationService.login(userRequestLogin);
        });

        assertEquals("error authenticating user, could not find user", exception.getMessage());
        verify(userRepository, times(1)).findUserByUsername(userRequestLogin.getUsername());
    }

    @Test
    void testGetRolesName_Success() {
        // Arrange
        User user = new User();
        Rol role1 = new Rol();
        role1.setRol("ROLE_USER");
        Rol role2 = new Rol();
        role2.setRol("ROLE_ADMIN");

        Set<Rol> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);
        user.setRoles(roles);

        // Act
        List<String> rolesNames = authenticationService.getRolesName(user);

        // Assert
        assertNotNull(rolesNames);
        assertEquals(2, rolesNames.size());
        assertTrue(rolesNames.contains("ROLE_USER"));
        assertTrue(rolesNames.contains("ROLE_ADMIN"));
    }
}