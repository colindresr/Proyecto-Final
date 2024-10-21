package com.proyecto.api.modelo.mongo;

import com.proyecto.api.dto.UserRepositoryDto;
import com.proyecto.api.modelo.EnumRol;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Document(collection = "user")
public class UserMongo {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private List<EnumRol> roles;

    public UserMongo() {
        this.roles = new ArrayList<>(Collections.singleton(EnumRol.USER));
    }


    public UserMongo( String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.roles = new ArrayList<>(Collections.singleton(EnumRol.USER));
    }

    public UserMongo(UserRepositoryDto userRepositoryDto) {
        this.name = userRepositoryDto.getName();
        System.out.println("Password userMongo 1 "+ userRepositoryDto.getPassword());
        this.password = new BCryptPasswordEncoder().encode(userRepositoryDto.getPassword());
        System.out.println("Constructor 2 " + this.password);
        this.email = userRepositoryDto.getEmail();
        this.roles = new ArrayList<>(Collections.singleton(EnumRol.USER));
    }


    // -------------------------- Getters y setters --------------------------------------
    public List<EnumRol> getRoles() {
        return roles;
    }

    public void setRoles(List<EnumRol> roles) {
        this.roles = roles;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateUser(UserRepositoryDto user){
        setName(user.getName());
        setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        setEmail(user.getEmail());
    }

    // -------------------------- toString --------------------------------------

    @Override
    public String toString() {
        return "UserMongo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    // -------------------------- equals and hashCode --------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMongo userMongo = (UserMongo) o;
        return Objects.equals(id, userMongo.id) && Objects.equals(name, userMongo.name) && Objects.equals(email, userMongo.email) && Objects.equals(password, userMongo.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(email);
        return result;
    }
}



