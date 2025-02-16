package com.mb.loan.service;

import com.mb.loan.model.Loan;
import com.mb.loan.model.LoanInstallment;
import com.mb.loan.model.PaymentResult;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {

    List<LoanInstallment>  createLoan(Loan loan, Float interestRate);

    List<Loan> listLoans(Loan loan);

    List<LoanInstallment>  listInstallment(LoanInstallment installment);

    PaymentResult pay(Long loanId, Double amount);
}
