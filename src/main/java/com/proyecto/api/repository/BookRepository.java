package com.proyecto.api.repository;

import com.proyecto.api.modelo.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book createBook(Book book);
    List<Book> getBooks();
    Optional<Book>findBookById(String idBook);
    Book updateBook(Book book);
    void deleteBook(String idBook);
}
