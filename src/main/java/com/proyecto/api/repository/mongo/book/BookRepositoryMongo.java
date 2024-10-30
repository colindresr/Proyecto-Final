package com.proyecto.api.repository.mongo.book;

import com.proyecto.api.modelo.Book;
import com.proyecto.api.modelo.mongo.BookMongo;
import com.proyecto.api.repository.BookRepository;
import com.proyecto.api.util.BookUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongo")
@RequiredArgsConstructor
@Repository
public class BookRepositoryMongo implements BookRepository {

    private final BookRepositoryJpaMongo mongo;
    private final BookUtil bookUtil;

    @Override
    public Book createBook(Book book) {
        BookMongo bookMongo = bookUtil.bookToBookMongo(book);
        BookMongo newBook = mongo.save(bookMongo);
        return bookUtil.bookMongoToBook(newBook);
    }

    @Override
    public List<Book> getBooks() {
        return mongo.findAll().stream()
                .map(bookUtil::bookMongoToBook)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findBookById(String idBook) {
        Optional<BookMongo> bookMongo = mongo.findById(idBook);
        return bookMongo.map(bookUtil::bookMongoToBook);
    }

    @Override
    public Book updateBook(Book book) {
        BookMongo bookMongo = bookUtil.bookToBookMongo(book);
        BookMongo newBook = mongo.save(bookMongo);
        return bookUtil.bookMongoToBook(newBook);
    }

    @Override
    public void deleteBook(String idBook) {
        mongo.deleteById(idBook);
    }
}
