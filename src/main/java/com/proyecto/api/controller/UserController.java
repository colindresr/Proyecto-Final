package com.proyecto.api.controller;

import com.proyecto.api.dto.RequestUpdate;
import com.proyecto.api.dto.ResponseUser;
import com.proyecto.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<ResponseUser>> getUsers() {
        List<ResponseUser> userResponses = userService.getUsers();
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("idUser") String idUser) {
        ResponseUser userResponse = userService.findUserById(idUser);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<ResponseUser> updateUser(@PathVariable("idUser") String idUser, @RequestBody RequestUpdate userRequestUpdate) {
        ResponseUser userResponse = userService.updateUser(idUser, userRequestUpdate);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("idUser") String idUser) {
        userService.deleteUser(idUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}