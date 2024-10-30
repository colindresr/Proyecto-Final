package com.proyecto.api.modelo.sql;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class LoanSqlId implements Serializable {

    private Long idBook;
    private Long idUser;
}
