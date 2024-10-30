package com.proyecto.api.controller;

import com.proyecto.api.modelo.Rol;
import com.proyecto.api.service.rol.RolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RolControllerTest {

    private RolService roleService;
    private RolController roleController;

    @BeforeEach
    void setUp() {
        roleService = Mockito.mock(RolService.class);
        roleController = new RolController(roleService);
    }

    @Test
    void testCreateRole() {
        Rol role = new Rol();
        role.setIdRol("1");
        role.setRol("ROLE_USER");

        when(roleService.createRole(any(Rol.class))).thenReturn(role);

        ResponseEntity<Rol> response = roleController.createRole(role);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(role, response.getBody());
    }

    @Test
    void testGetRoles() {
        Rol role1 = new Rol();
        role1.setIdRol("1");
        role1.setRol("ROLE_USER");

        Rol role2 = new Rol();
        role2.setIdRol("2");
        role2.setRol("ROLE_ADMIN");

        List<Rol> roles = Arrays.asList(role1, role2);
        when(roleService.getRoles()).thenReturn(roles);

        ResponseEntity<List<Rol>> response = roleController.getRoles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roles, response.getBody());
    }

    @Test
    void testGetRole() {
        Rol role = new Rol();
        role.setIdRol("1");
        role.setRol("ROLE_USER");

        when(roleService.findRoleById("1")).thenReturn(role);

        ResponseEntity<Rol> response = roleController.getRole("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(role, response.getBody());
    }

    @Test
    void testUpdateRole() {
        Rol role = new Rol();
        role.setIdRol("1");
        role.setRol("ROLE_USER");

        when(roleService.updateRole(eq("1"), any(Rol.class))).thenReturn(role);

        ResponseEntity<Rol> response = roleController.updateRole("1", role);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(role, response.getBody());
    }

    @Test
    void testDeleteRole() {
        String roleId = "1";
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Role deleted successfully");

        doNothing().when(roleService).deleteRole(roleId);

        ResponseEntity<Map<String, String>> response = roleController.deleteRole(roleId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}