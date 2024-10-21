package com.proyecto.api.modelo.sql;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long idBook;
    private String title;
    private String author;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    public Book() {
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(idBook, book.idBook) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(users, book.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, title, author, users);
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
