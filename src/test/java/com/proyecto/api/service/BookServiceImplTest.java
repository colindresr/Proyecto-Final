package com.proyecto.api.service;

import com.proyecto.api.modelo.Book;
import com.proyecto.api.repository.BookRepository;
import com.proyecto.api.service.book.BookServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(bookService, "profile", "postgres");
    }

    @Test
    void testCreateBook_Success() {
        // Arrange
        Book book = new Book();
        book.setTitle("La Tercera");
        book.setAuthor("Alejandro Wall");

        when(bookRepository.createBook(book)).thenReturn(book);

        // Act
        Book result = bookService.createBook(book);

        // Assert
        assertNotNull(result);
        assertEquals("La Tercera", result.getTitle());
        verify(bookRepository, times(1)).createBook(book);
    }

    @Test
    void testGetBooks_Success() {
        // Arrange
        Book book = new Book();
        book.setTitle("La Tercera");
        book.setAuthor("Alejandro Wall");

        when(bookRepository.getBooks()).thenReturn(Collections.singletonList(book));

        // Act
        List<Book> result = bookService.getBooks();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("La Tercera", result.get(0).getTitle());
        verify(bookRepository, times(1)).getBooks();
    }

    @Test
    void testFindBookById_Success() {
        // Arrange
        String idBook = "1";
        Book book = new Book();
        book.setTitle("La Tercera");
        when(bookRepository.findBookById(idBook)).thenReturn(Optional.of(book));

        // Act
        Book result = bookService.findBookById(idBook);

        // Assert
        assertNotNull(result);
        assertEquals("La Tercera", result.getTitle());
        verify(bookRepository, times(1)).findBookById(idBook);
    }

    @Test
    void testFindBookById_NotFound() {
        // Arrange
        String idBook = "1";
        when(bookRepository.findBookById(idBook)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            bookService.findBookById(idBook);
        });

        assertEquals("Book not found with ID: 1", exception.getMessage());
        verify(bookRepository, times(1)).findBookById(idBook);
    }

    @Test
    void testUpdateBook_Success() {
        // Arrange
        String idBook = "1";
        Book bookToUpdate = new Book();
        bookToUpdate.setTitle("Updated Book");
        bookToUpdate.setAuthor("Updated Author");

        Book existingBook = new Book();
        existingBook.setTitle("Original Book");
        existingBook.setAuthor("Original Author");

        when(bookRepository.findBookById(idBook)).thenReturn(Optional.of(existingBook));
        when(bookRepository.updateBook(existingBook)).thenReturn(existingBook);

        // Act
        Book result = bookService.updateBook(idBook, bookToUpdate);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Book", result.getTitle());
        verify(bookRepository, times(1)).findBookById(idBook);
        verify(bookRepository, times(1)).updateBook(existingBook);
    }

    @Test
    void testUpdateBook_NotFound() {
        // Arrange
        String idBook = "1";
        Book bookToUpdate = new Book();
        when(bookRepository.findBookById(idBook)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            bookService.updateBook(idBook, bookToUpdate);
        });

        assertEquals("Book not found with ID: 1", exception.getMessage());
        verify(bookRepository, times(1)).findBookById(idBook);
    }

    @Test
    void testDeleteBook_Success() {
        // Arrange
        String idBook = "1";
        Book book = new Book();
        when(bookRepository.findBookById(idBook)).thenReturn(Optional.of(book));

        // Act
        bookService.deleteBook(idBook);

        // Assert
        verify(bookRepository, times(1)).findBookById(idBook);
        verify(bookRepository, times(1)).deleteBook(idBook);
    }

    @Test
    void testDeleteBook_NotFound() {
        // Arrange
        String idBook = "1";
        when(bookRepository.findBookById(idBook)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            bookService.deleteBook(idBook);
        });

        assertEquals("Book not found with ID: 1", exception.getMessage());
        verify(bookRepository, times(1)).findBookById(idBook);
    }
}