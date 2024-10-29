package com.proyecto.api.modelo.mongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
public class BookMongo {

    @Id
    private String idBook;

    private String title;
    private String author;
}
