package com.proyecto.api.controller;

import com.proyecto.api.modelo.Rol;
import com.proyecto.api.service.rol.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/rol")
public class RolController {

    private final RolService roleService;

    @PostMapping
    public ResponseEntity<Rol> createRole(@RequestBody Rol role) {
        Rol createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rol>> getRoles() {
        List<Rol> roles = roleService.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{idRol}")
    public ResponseEntity<Rol> getRole(@PathVariable("idRol") String idRole) {
        Rol role = roleService.findRoleById(idRole);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }


    @PutMapping("{idRol}")
    public ResponseEntity<Rol> updateRole(@PathVariable("idRol") String idRole, @RequestBody Rol role) {
        Rol updatedRole = roleService.updateRole(idRole, role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<Map<String, String>> deleteRole(@PathVariable("idRole") String idRole) {
        roleService.deleteRole(idRole); // Elimina el rol utilizando el servicio de roles.
        Map<String, String> response = new HashMap<>();
        response.put("message", "Role deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}