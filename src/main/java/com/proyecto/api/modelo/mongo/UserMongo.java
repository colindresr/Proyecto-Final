package com.proyecto.api.modelo.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Document(collection = "users")
public class UserMongo implements UserDetails {
    @Id
    private String id;

    private String name;
    private String username;
    private String email;
    private String password;

    @Field(name = "date_creation") // Mapea el campo "date_creation" en MongoDB a esta propiedad.
    private LocalDateTime dateCreation; // Fecha de creación de la cuenta del usuario.

    @Field(name = "date_updated") // Mapea el campo "date_updated" en MongoDB a esta propiedad.
    private LocalDateTime dateUpdate; // Fecha de la última actualización de la cuenta del usuario.

    private Set<RolMongo> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}



