package com.proyecto.api.repository.mongo.book;

import com.proyecto.api.modelo.mongo.BookMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepositoryJpaMongo extends MongoRepository<BookMongo, String> {
}
