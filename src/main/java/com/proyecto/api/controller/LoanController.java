package com.proyecto.api.controller;

import com.proyecto.api.modelo.Loan;
import com.proyecto.api.service.loan.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/{idBook}")
    public ResponseEntity<Loan> createLoan(@PathVariable String idBook, @RequestBody Loan loan) {
        Loan newLoan = loanService.createLoan(idBook, loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loanPostgres = loanService.getLoans();
        return new ResponseEntity<>(loanPostgres, HttpStatus.OK);
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<Loan> getLoanById(@PathVariable String idBook) {
        Loan loan = loanService.findLoanById(idBook); // Busca el préstamo utilizando el servicio de préstamos.
        return new ResponseEntity<>(loan, HttpStatus.OK); // Devuelve el préstamo encontrado con estado HTTP 200.
    }

    @PutMapping("/{idBook}")
    public ResponseEntity<Loan> updateLoan(@PathVariable String idBook, @RequestBody Loan loan) {
        Loan newLoan = loanService.updateLoan(idBook, loan); // Actualiza el préstamo utilizando el servicio de préstamos.
        return new ResponseEntity<>(newLoan, HttpStatus.OK); // Devuelve el préstamo actualizado con estado HTTP 200.
    }

    @DeleteMapping("/{idBook}")
    public ResponseEntity<Map<String, String>> deleteLoan(@PathVariable String idBook) {
        loanService.deleteLoan(idBook); // Elimina el préstamo utilizando el servicio de préstamos.
        Map<String, String> response = new HashMap<>();
        response.put("message", "Loan deleted successfully"); // Mensaje de éxito.
        return new ResponseEntity<>(response, HttpStatus.OK); // Devuelve el mensaje con estado HTTP 200.
    }
}
