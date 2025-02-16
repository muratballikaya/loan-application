package com.mb.loan.mapper;

import com.mb.loan.dto.request.ListInstallmentDto;
import com.mb.loan.model.Loan;
import com.mb.loan.model.LoanInstallment;

public class LoanInstallmentMapper {

    public static LoanInstallment mapToLoanInstallment(ListInstallmentDto listInstallmentDto){
        LoanInstallment loanInstallment = new LoanInstallment();
        loanInstallment.setLoan(new Loan(listInstallmentDto.getLoanId()));
        loanInstallment.setPaid(loanInstallment.isPaid());
        return loanInstallment;
    }
}
