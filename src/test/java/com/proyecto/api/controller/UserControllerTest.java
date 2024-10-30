package com.proyecto.api.controller;

import com.proyecto.api.dto.RequestUpdate;
import com.proyecto.api.dto.ResponseUser;
import com.proyecto.api.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        // Arrange
        ResponseUser userResponse = new ResponseUser();
        when(userService.getUsers()).thenReturn(Collections.singletonList(userResponse));

        // Act
        ResponseEntity<List<ResponseUser>> response = userController.getUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(userResponse, response.getBody().get(0));
    }

    @Test
    void testGetUser() {
        // Arrange
        String userId = "123";
        ResponseUser userResponse = new ResponseUser();
        when(userService.findUserById(userId)).thenReturn(userResponse);

        // Act
        ResponseEntity<ResponseUser> response = userController.getUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        String userId = "123";
        RequestUpdate userRequestUpdate = new RequestUpdate();
        ResponseUser updatedUserResponse = new ResponseUser();
        when(userService.updateUser(eq(userId), any(RequestUpdate.class))).thenReturn(updatedUserResponse);

        // Act
        ResponseEntity<ResponseUser> response = userController.updateUser(userId, userRequestUpdate);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUserResponse, response.getBody());
    }

    @Test
    void testDeleteUser() {
        // Arrange
        String userId = "123";

        // Act
        ResponseEntity<Map<String, String>> response = userController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("User deleted successfully", response.getBody().get("message"));

        // Verifica que el método deleteUser en el servicio fue llamado
        verify(userService, times(1)).deleteUser(userId);
    }
}
