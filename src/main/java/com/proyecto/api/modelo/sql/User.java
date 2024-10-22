package com.proyecto.api.modelo.sql;

import com.proyecto.api.dto.UserRepositoryDto;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_book", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_user"), // Columna para User
            inverseJoinColumns = @JoinColumn(name = "id_book")) // Columna para Book
    private List<Book> books;

    @OneToMany(mappedBy = "user") // Corregido "loan" a "user"
    private List<Loan> loans;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_rol",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private List<Rol> roles;

    public User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        System.out.println("Password user 1 " + password);
        this.password = new BCryptPasswordEncoder().encode(password);
        System.out.println("Constructor user 1 " + this.password);
        this.email = email;
    }

    public User(UserRepositoryDto userRepositoryDto) {
        this.name = userRepositoryDto.getName();
        System.out.println("Password user 2 "+ userRepositoryDto.getPassword());
        this.password = new BCryptPasswordEncoder().encode(userRepositoryDto.getPassword());
        System.out.println("Constructor user 2 "+ this.password);
        this.email = userRepositoryDto.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public void updateUser(UserRepositoryDto user){
        setName(user.getName());
        setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        setEmail(user.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(books, user.books) && Objects.equals(loans, user.loans) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, books, loans, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
