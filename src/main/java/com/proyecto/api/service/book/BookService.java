package com.proyecto.api.service.book;

import com.proyecto.api.modelo.Book;

import java.util.List;

public interface BookService {

        Book createBook(Book book);

    List<Book> getBooks();

    Book findBookById(String idBook);

    Book updateBook(String idBook, Book book);

    void deleteBook(String idBook);
}
