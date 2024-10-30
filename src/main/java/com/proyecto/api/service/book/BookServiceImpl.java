package com.proyecto.api.service.book;

import com.proyecto.api.modelo.Book;
import com.proyecto.api.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private static final String BOOK_NOT_FOUND = "Book not found with ID: ";

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public Book createBook(Book book) {
        return bookRepository.createBook(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    @Override
    public Book findBookById(String idBook) {
        validateIdFormat(idBook);

        return bookRepository.findBookById(idBook)
                .orElseThrow(() -> new EntityNotFoundException(BOOK_NOT_FOUND + idBook));
    }

    @Override
    public Book updateBook(String idBook, Book book) {
        validateIdFormat(idBook);

        Book bookFound = bookRepository.findBookById(idBook)
                .orElseThrow(() -> new EntityNotFoundException(BOOK_NOT_FOUND + idBook));

        bookFound.setTitle(book.getTitle());
        bookFound.setAuthor(book.getAuthor());
        return bookRepository.updateBook(bookFound);
    }

    @Override
    public void deleteBook(String idBook) {
        validateIdFormat(idBook);

        if(bookRepository.findBookById(idBook).isEmpty()) {
            throw new EntityNotFoundException(BOOK_NOT_FOUND + idBook);
        }
        bookRepository.deleteBook(idBook);
    }

    private void validateIdFormat(String idBook) {
        if(profile.equals("postgres")) {
            try {
                Long id = Long.parseLong(idBook);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid idBook format for Postgres: " + idBook);
            }
        }
    }
}
