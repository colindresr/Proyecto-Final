package com.proyecto.api.modelo.sql;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "loans")
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan")
    private Long idLoan;

    @Column(name = "date_loan")
    private LocalDateTime dateLoan;

    @Column(name = "date_devolution")
    private LocalDateTime dateDevolution;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book; // La relación ya cubre el id del libro

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Loan() {
    }

    public Long getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(Long idLoan) {
        this.idLoan = idLoan;
    }

    public LocalDateTime getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(LocalDateTime dateLoan) {
        this.dateLoan = dateLoan;
    }

    public LocalDateTime getDateDevolution() {
        return dateDevolution;
    }

    public void setDateDevolution(LocalDateTime dateDevolution) {
        this.dateDevolution = dateDevolution;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(idLoan, loan.idLoan) && Objects.equals(dateLoan, loan.dateLoan) && Objects.equals(dateDevolution, loan.dateDevolution) && Objects.equals(book, loan.book) && Objects.equals(user, loan.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLoan, dateLoan, dateDevolution, book, user);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "idLoan=" + idLoan +
                ", dateLoan=" + dateLoan +
                ", dateDevolution=" + dateDevolution +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
