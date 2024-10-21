package com.proyecto.api.modelo.sql;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;
    private String rol;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Rol(){
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + idRol +
                ", rol='" + rol + '\'' +
                '}';
    }
}
