package com.proyecto.api.repository.postgres.loan;

import com.proyecto.api.modelo.Loan;
import com.proyecto.api.modelo.sql.LoanSql;
import com.proyecto.api.modelo.sql.LoanSqlId;
import com.proyecto.api.repository.LoanRepository;
import com.proyecto.api.util.LoanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("sql")
@RequiredArgsConstructor
@Repository
public class LoanRepositorySql implements LoanRepository {

    private final LoanRepositoryJpa loanRepositoryJpa;
    private final LoanUtil loanUtil;

    @Override
    public Loan createLoan(Loan loan) {
        LoanSql loanPostgres = loanUtil.loanToLoanPostgres(loan);
        LoanSql newLoan = loanRepositoryJpa.save(loanPostgres);
        return loanUtil.loanPostgresToLoan(newLoan);
    }

    @Override
    public List<Loan> getLoans() {
        return loanRepositoryJpa.findAll().stream()
                .map(loanUtil::loanPostgresToLoan)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Loan> findLoanById(String idUser, String idBook) {
        LoanSqlId id = new LoanSqlId();
        id.setIdUser(Long.parseLong(idUser));
        id.setIdBook(Long.parseLong(idBook));
        Optional<LoanSql> loanPostgres = loanRepositoryJpa.findById(id);
        return loanPostgres.map(loanUtil::loanPostgresToLoan);
    }

    @Override
    public Loan updateLoan(Loan loan) {
        LoanSql loanPostgres = loanUtil.loanToLoanPostgres(loan);
        LoanSql newLoan = loanRepositoryJpa.save(loanPostgres);
        return loanUtil.loanPostgresToLoan(newLoan);
    }

    @Override
    public void deleteLoan(String idUser, String idBook) {
        LoanSqlId id = new LoanSqlId();
        id.setIdUser(Long.parseLong(idUser));
        id.setIdBook(Long.parseLong(idBook));
        loanRepositoryJpa.deleteById(id);
    }
}
