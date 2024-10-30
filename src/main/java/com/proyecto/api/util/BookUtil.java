package com.proyecto.api.util;

import com.proyecto.api.modelo.Book;
import com.proyecto.api.modelo.mongo.BookMongo;
import com.proyecto.api.modelo.sql.BookSql;
import org.springframework.stereotype.Component;

@Component
public class BookUtil {

    public BookSql bookToBookPostgres(Book book) {
        BookSql bookPostgres = new BookSql();
        bookPostgres.setIdBook((book.getIdBook() != null && !book.getIdBook().isEmpty())
                ? Long.parseLong(book.getIdBook()) : null);
        bookPostgres.setTitle(book.getTitle());
        bookPostgres.setAuthor(book.getAuthor());
        return bookPostgres;
    }

    public Book bookPostgresToBook(BookSql bookPostgres) {
        Book book = new Book();
        book.setIdBook(String.valueOf(bookPostgres.getIdBook()));
        book.setTitle(bookPostgres.getTitle());
        book.setAuthor(bookPostgres.getAuthor());
        return book;
    }

    public BookMongo bookToBookMongo(Book book) {
        BookMongo bookMongo = new BookMongo();
        bookMongo.setIdBook(book.getIdBook());
        bookMongo.setTitle(book.getTitle());
        bookMongo.setAuthor(book.getAuthor());
        return bookMongo;
    }

    public Book bookMongoToBook(BookMongo bookMongo) {
        Book book = new Book();
        book.setIdBook(bookMongo.getIdBook());
        book.setTitle(bookMongo.getTitle());
        book.setAuthor(bookMongo.getAuthor());
        return book;
    }
}
