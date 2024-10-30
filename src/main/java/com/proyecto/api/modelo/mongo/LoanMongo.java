package com.proyecto.api.modelo.mongo;

import com.proyecto.api.modelo.sql.LoanSqlId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "loans")
public class LoanMongo {

    @Id
    @Embedded
    private LoanMongoId idLoan;

    @Field(name = "loan_date")
    private LocalDateTime loanDate;

    @Field(name = "return_date")
    private LocalDateTime returnDate;

}
