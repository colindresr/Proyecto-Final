package com.proyecto.api.service;

import com.proyecto.api.modelo.Rol;
import com.proyecto.api.repository.RolRepository;
import com.proyecto.api.service.rol.RolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import jakarta.persistence.EntityNotFoundException;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RolServiceImplTest {

    @Mock
    private RolRepository roleRepository;

    @InjectMocks
    private RolServiceImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(roleService, "profile", "postgres");
    }

    @Test
    void testFindRoleById_Success() {
        // Arrange
        String idRole = "1";
        Rol role = new Rol();
        role.setRol("USER");
        when(roleRepository.findRoleById(idRole)).thenReturn(Optional.of(role));


        Rol result = roleService.findRoleById(idRole);

        // Assert
        assertNotNull(result);
        assertEquals("USER", result.getRol());
        verify(roleRepository, times(1)).findRoleById(idRole);
    }

    @Test
    void testFindRoleById_NotFound() {
        // Arrange
        String idRole = "1";
        when(roleRepository.findRoleById(idRole)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleService.findRoleById(idRole);
        });

        assertEquals("Role not found with id 1", exception.getMessage());
        verify(roleRepository, times(1)).findRoleById(idRole);
    }

    @Test
    void testUpdateRole_Success() {
        // Arrange
        String idRole = "1";
        Rol role = new Rol();
        role.setRol("ADMIN");
        Rol existingRole = new Rol();
        existingRole.setRol("USER");
        when(roleRepository.findRoleById(idRole)).thenReturn(Optional.of(existingRole));
        when(roleRepository.updateRol(existingRole)).thenReturn(existingRole);

        // Act
        Rol result = roleService.updateRole(idRole, role);

        // Assert
        assertNotNull(result);
        assertEquals("ADMIN", result.getRol());
        verify(roleRepository, times(1)).findRoleById(idRole);
        verify(roleRepository, times(1)).updateRol(existingRole);
    }

    @Test
    void testUpdateRole_NotFound() {
        // Arrange
        String idRole = "1";
        Rol role = new Rol();
        when(roleRepository.findRoleById(idRole)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleService.updateRole(idRole, role);
        });

        assertEquals("Role not found with id 1", exception.getMessage());
        verify(roleRepository, times(1)).findRoleById(idRole);
    }

    @Test
    void testDeleteRole_Success() {
        // Arrange
        String idRole = "1";
        Rol role = new Rol();
        when(roleRepository.findRoleById(idRole)).thenReturn(Optional.of(role));

        // Act
        roleService.deleteRole(idRole);

        // Assert
        verify(roleRepository, times(1)).findRoleById(idRole);
        verify(roleRepository, times(1)).deleteRol(idRole);
    }

    @Test
    void testDeleteRole_NotFound() {
        // Arrange
        String idRole = "1";
        when(roleRepository.findRoleById(idRole)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleService.deleteRole(idRole);
        });

        assertEquals("Role not found with id 1", exception.getMessage());
        verify(roleRepository, times(1)).findRoleById(idRole);
    }

}
