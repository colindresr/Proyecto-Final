package com.proyecto.api.repository.mongo.loan;

import com.proyecto.api.modelo.Loan;
import com.proyecto.api.modelo.mongo.LoanMongo;
import com.proyecto.api.modelo.mongo.LoanMongoId;
import com.proyecto.api.repository.LoanRepository;
import com.proyecto.api.repository.postgres.loan.LoanRepositoryJpa;
import com.proyecto.api.util.LoanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mongo")
@RequiredArgsConstructor
@Service
public class LoanRepositoryMongo implements LoanRepository {

    private final LoanRepositoryJpaMongo mongo;
    private final LoanUtil loanUtil;

    @Override
    public Loan createLoan(Loan loan) {
        LoanMongo loanMongo = loanUtil.loanToLoanMongo(loan);
        LoanMongo newMongo = mongo.save(loanMongo);
        return loanUtil.loanMongoToLoan(newMongo);
    }

    @Override
    public List<Loan> getLoans() {
        return mongo.findAll().stream()
                .map(loanUtil::loanMongoToLoan)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Loan> findLoanById(String idUser, String idBook) {
        LoanMongoId id = new LoanMongoId();
        id.setIdUser(idUser);
        id.setIdBook(idBook);
        Optional<LoanMongo> loanMongo = mongo.findById(id);
        return loanMongo.map(loanUtil::loanMongoToLoan);
    }

    @Override
    public Loan updateLoan(Loan loan) {
        LoanMongo loanMongo = loanUtil.loanToLoanMongo(loan);
        LoanMongo newMongo = mongo.save(loanMongo);
        return loanUtil.loanMongoToLoan(newMongo);
    }

    @Override
    public void deleteLoan(String idUser, String idBook) {
        LoanMongoId id = new LoanMongoId();
        id.setIdUser(idUser);
        id.setIdBook(idBook);
        mongo.deleteById(id);
    }
}
