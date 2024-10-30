package com.proyecto.api.repository;

import com.proyecto.api.modelo.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    Loan createLoan(Loan loan);
    List<Loan> getLoans();
    Optional<Loan> findLoanById(String idUser, String idLoan);
    Loan updateLoan(Loan loan);
    void deleteLoan(String idUser, String idBook);
}
