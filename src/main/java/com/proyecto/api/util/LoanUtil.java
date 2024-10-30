package com.proyecto.api.util;

import com.proyecto.api.modelo.Loan;
import com.proyecto.api.modelo.mongo.LoanMongo;
import com.proyecto.api.modelo.mongo.LoanMongoId;
import com.proyecto.api.modelo.sql.LoanSql;
import com.proyecto.api.modelo.sql.LoanSqlId;
import org.springframework.stereotype.Component;

@Component
public class LoanUtil {

    public LoanSql loanToLoanPostgres(Loan loan) {
        LoanSql loanPostgres = new LoanSql();
        LoanSqlId loanPostgresId = new LoanSqlId();
        loanPostgresId.setIdUser((loan.getIdUser() != null && !loan.getIdUser().isEmpty())
                ? Long.parseLong(loan.getIdUser()) : null);
        loanPostgresId.setIdBook((loan.getIdBook() != null && !loan.getIdBook().isEmpty())
                ? Long.parseLong(loan.getIdBook()) : null);
        loanPostgres.setIdLoan(loanPostgresId);
        loanPostgres.setLoanDate(loan.getLoanDate());
        loanPostgres.setReturnDate(loan.getReturnDate());
        return loanPostgres;
    }

    public Loan loanPostgresToLoan(LoanSql loanPostgres) {
        Loan loan = new Loan();
        loan.setIdUser(String.valueOf(loanPostgres.getIdLoan().getIdUser()));
        loan.setIdBook(String.valueOf(loanPostgres.getIdLoan().getIdBook()));
        loan.setLoanDate(loanPostgres.getLoanDate());
        loan.setReturnDate(loanPostgres.getReturnDate());
        return loan;
    }

    public LoanMongo loanToLoanMongo(Loan loan) {
        LoanMongo loanMongo = new LoanMongo();
        LoanMongoId loanMongoId = new LoanMongoId();
        loanMongoId.setIdBook(loan.getIdBook());
        loanMongoId.setIdUser(loan.getIdUser());
        loanMongo.setIdLoan(loanMongoId);
        loanMongo.setLoanDate(loan.getLoanDate());
        loanMongo.setReturnDate(loan.getReturnDate());
        return loanMongo;
    }

    public Loan loanMongoToLoan(LoanMongo loanMongo) {
        Loan loan = new Loan();
        loan.setIdUser(loanMongo.getIdLoan().getIdUser());
        loan.setIdBook(loanMongo.getIdLoan().getIdBook());
        loan.setLoanDate(loanMongo.getLoanDate());
        loan.setReturnDate(loanMongo.getReturnDate());
        return loan;
    }
}
