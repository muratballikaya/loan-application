package com.mb.loan.controller;


import com.mb.loan.dto.request.CreateLoanDto;
import com.mb.loan.dto.request.ListInstallmentDto;
import com.mb.loan.dto.request.ListLoanDto;
import com.mb.loan.dto.request.PaymentDto;

import com.mb.loan.model.Loan;
import com.mb.loan.model.LoanInstallment;
import com.mb.loan.model.PaymentResult;
import com.mb.loan.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testListLoans() {
        // Arrange
        ListLoanDto listLoanDto = new ListLoanDto();
        List<Loan> loans = Collections.singletonList(new Loan());
        when(loanService.listLoans(any(Loan.class))).thenReturn(loans);

        // Act
        ResponseEntity<?> response = loanController.listLoans(listLoanDto);

        // Assert
        assertEquals(loans, response.getBody());
    }

    @Test
    void testListInstallments() {
        // Arrange
        ListInstallmentDto listInstallmentDto = new ListInstallmentDto();
        List<LoanInstallment> loanInstallments = Collections.singletonList(new LoanInstallment());
        when(loanService.listInstallment(any(LoanInstallment.class))).thenReturn(loanInstallments);

        // Act
        ResponseEntity<?> response = loanController.listInstalment(listInstallmentDto);

        // Assert
        assertEquals(loanInstallments, response.getBody());
    }

}
