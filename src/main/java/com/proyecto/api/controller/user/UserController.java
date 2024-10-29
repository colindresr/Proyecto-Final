package com.proyecto.api.controller.user;


import com.proyecto.api.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserCreateDto>>getAllUsers(){
        try{
            List<UserCreateDto> usersFound = userService.getAllUsers();
            if(usersFound.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usersFound, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity("Error in the server", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserCreateDto> findUserById(@PathVariable("idUser") String idUser){
        try{
            return new ResponseEntity<>(userService.findUserById(idUser), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("User " + idUser + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserCreateDto> createUser(@RequestBody UserRegisterDto user){
        try{
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity("Error in the server", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("idUser") String idUser, @RequestBody UserRegisterDto user){
        try{
            Boolean isUpdated = userService.updateUser(idUser, user);
            if(isUpdated){
                return new ResponseEntity<>(isUpdated, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        }catch (NoSuchElementException e){
            return new ResponseEntity("User " + idUser + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("idUser") String idUser) {
        return new ResponseEntity<>(userService.deleteUser(idUser), HttpStatus.OK);
    }
}
