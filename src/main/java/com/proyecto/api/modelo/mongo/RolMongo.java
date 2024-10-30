package com.proyecto.api.modelo.mongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "roles")
public class RolMongo  {

    @Id
    private String id;

    private String rol;

}
