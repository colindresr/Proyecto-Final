package com.proyecto.api.repository.postgres.book;

import com.proyecto.api.modelo.sql.BookSql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryJpa extends JpaRepository<BookSql, Long> {
}
