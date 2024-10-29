package com.proyecto.api.modelo.sql;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "loans")
public class LoanSql {


    @Id
    private Long idLoan;

    @Column(name = "loan_date")
    private LocalDateTime loanDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;


    private Long idBook;
    private Long idUser;


}


