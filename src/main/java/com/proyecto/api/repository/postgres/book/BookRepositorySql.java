package com.proyecto.api.repository.postgres.book;


import com.proyecto.api.modelo.Book;
import com.proyecto.api.modelo.sql.BookSql;
import com.proyecto.api.repository.BookRepository;
import com.proyecto.api.util.BookUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("sql")
@RequiredArgsConstructor
@Repository
public class BookRepositorySql implements BookRepository {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final BookUtil bookUtil;

    @Override
    public Book createBook(Book book) {
        BookSql bookPostgres = bookUtil.bookToBookPostgres(book);
        BookSql newBook = bookRepositoryJpa.save(bookPostgres);
        return bookUtil.bookPostgresToBook(newBook);
    }


    @Override
    public List<Book> getBooks() {
        return bookRepositoryJpa.findAll().stream()
                .map(bookUtil::bookPostgresToBook)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Book> findBookById(String idBook) {
        Optional<BookSql> bookPostgres = bookRepositoryJpa.findById(Long.parseLong(idBook));
        return bookPostgres.map(bookUtil::bookPostgresToBook);
    }


    @Override
    public Book updateBook(Book book) {
        BookSql bookPostgres = bookUtil.bookToBookPostgres(book);
        BookSql newBook = bookRepositoryJpa.save(bookPostgres);
        return bookUtil.bookPostgresToBook(newBook);
    }

    @Override
    public void deleteBook(String idBook) {
        bookRepositoryJpa.deleteById(Long.parseLong(idBook));
    }

}
