package com.proyecto.api.repository.postgres.loan;

import com.proyecto.api.modelo.sql.LoanSql;
import com.proyecto.api.modelo.sql.LoanSqlId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepositoryJpa extends JpaRepository<LoanSql, LoanSqlId> {
}
