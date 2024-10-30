package com.proyecto.api.service.auth;

import com.proyecto.api.dto.RequestLogin;
import com.proyecto.api.dto.RequestUser;
import com.proyecto.api.modelo.Rol;
import com.proyecto.api.modelo.User;
import com.proyecto.api.repository.RolRepository;
import com.proyecto.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RolRepository roleRepository;

    @Value("${spring.profiles.active}")
    private String profile;

    public User signup(RequestUser userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setDateCreation(LocalDateTime.now());
        user.setDateUpdate(LocalDateTime.now());

        Rol role = roleRepository.findRolByName("ROLE_USER")
                .orElseThrow(() -> new EntityNotFoundException("error creating user, could not assign a role"));
        user.getRoles().add(role);

        return userRepository.createUser(user);
    }

    public User login(RequestLogin userRequestLogin) {
        // Realiza la autenticación
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequestLogin.getUsername(),
                        userRequestLogin.getPassword()
                )
        );

        return userRepository.findUserByUsername(userRequestLogin.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("error authenticating user, could not find user"));
    }

    public List<String> getRolesName(User user) {
        return user.getRoles().stream()
                .map(Rol::getRol)
                .collect(Collectors.toList());
    }
}
