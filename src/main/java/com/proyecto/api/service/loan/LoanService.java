package com.proyecto.api.service.loan;

import com.proyecto.api.modelo.Loan;

import java.util.List;

public interface LoanService {

    Loan createLoan(String idBook, Loan loan);

    List<Loan> getLoans();

    Loan findLoanById(String idBook);

    Loan updateLoan(String idBook, Loan loan);

    void deleteLoan(String idBook);
}
