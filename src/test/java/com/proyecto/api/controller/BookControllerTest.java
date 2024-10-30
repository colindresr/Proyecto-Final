package com.proyecto.api.controller;

import com.proyecto.api.modelo.Book;
import com.proyecto.api.service.book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTest {

    private BookService bookService;
    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookService = Mockito.mock(BookService.class);
        bookController = new BookController(bookService);
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setIdBook("1");
        book.setTitle("Test Book");
        book.setAuthor("Test Author");

        when(bookService.createBook(any(Book.class))).thenReturn(book);

        ResponseEntity<Book> response = bookController.createBook(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book();
        book1.setIdBook("1");
        book1.setTitle("Test Book 1");
        book1.setAuthor("Test Author 1");

        Book book2 = new Book();
        book2.setIdBook("2");
        book2.setTitle("Test Book 2");
        book2.setAuthor("Test Author 2");

        List<Book> books = Arrays.asList(book1, book2);
        when(bookService.getBooks()).thenReturn(books);

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setIdBook("1");
        book.setTitle("Test Book");
        book.setAuthor("Test Author");

        when(bookService.findBookById("1")).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void testUpdateBook() {
        Book book = new Book();
        book.setIdBook("1");
        book.setTitle("Updated Book");
        book.setAuthor("Updated Author");

        when(bookService.updateBook(eq("1"), any(Book.class))).thenReturn(book);

        ResponseEntity<Book> response = bookController.updateBook("1", book);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void testDeleteBook() {
        String bookId = "1";
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Book deleted successfully");

        doNothing().when(bookService).deleteBook(bookId);

        ResponseEntity<Map<String, String>> response = bookController.deleteBook(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}