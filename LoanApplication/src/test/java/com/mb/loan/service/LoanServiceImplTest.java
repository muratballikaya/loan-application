package com.mb.loan.service;


import com.mb.customer.model.Customer;
import com.mb.customer.service.CustomerService;
import com.mb.loan.exception.LoanAlreadyPayedException;
import com.mb.loan.model.Loan;
import com.mb.loan.model.LoanInstallment;
import com.mb.loan.model.PaymentResult;
import com.mb.loan.repository.LoanInstalmentRepository;
import com.mb.loan.repository.LoanRepository;
import com.mb.loan.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoanServiceImplTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private LoanInstalmentRepository loanInstalmentRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testListLoans() {
        // Arrange
        Loan exampleLoan = new Loan();
        List<Loan> loans = Collections.singletonList(new Loan());
        when(loanRepository.findAll(any(Example.class))).thenReturn(loans);

        // Act
        List<Loan> result = loanService.listLoans(exampleLoan);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testListInstallment() {
        // Arrange
        LoanInstallment exampleInstallment = new LoanInstallment();
        List<LoanInstallment> installments = Collections.singletonList(new LoanInstallment());
        when(loanInstalmentRepository.findAll(any(Example.class))).thenReturn(installments);

        // Act
        List<LoanInstallment> result = loanService.listInstallment(exampleInstallment);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testPay() {
        // Arrange
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setNumberOfInstalment(10);
        loan.setLoanAmount(1000.0);
        loan.setPaid(false);
        Customer customer = new Customer();
        customer.setId(1L);
        loan.setCustomer(customer);

        List<LoanInstallment> installments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LoanInstallment installment = new LoanInstallment();
            installment.setAmount(100.0);
            installment.setPaid(false);
            installments.add(installment);
        }

        when(loanRepository.findById(any(Long.class))).thenReturn(Optional.of(loan));
        when(loanInstalmentRepository.findByLoanAndIsPaid(any(Loan.class), eq(false))).thenReturn(installments);

        // Act
        PaymentResult result = loanService.pay(1L, 1000.0);

        // Assert
        assertNotNull(result);

        loan.setPaid(true);
        assertThrows(LoanAlreadyPayedException.class, () -> loanService.pay(1L, 1000.0));


    }
}