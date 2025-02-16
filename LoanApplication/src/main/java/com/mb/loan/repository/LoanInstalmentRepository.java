package com.mb.loan.repository;

import com.mb.loan.model.Loan;
import com.mb.loan.model.LoanInstallment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanInstalmentRepository extends JpaRepository<LoanInstallment, Long> {
    List<LoanInstallment> findByLoan(Loan loan);
    List<LoanInstallment> findByLoanAndIsPaid(Loan loan, boolean b);
}
