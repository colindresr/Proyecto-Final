package com.proyecto.api.modelo.mongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "loans")
public class LoanMongo {

    @Id
    private String idLoan;

    @Field(name = "loan_date")
    private LocalDateTime loanDate;

    @Field(name = "return_date")
    private LocalDateTime returnDate;

    private String idBook;
    private String idUser;
}
