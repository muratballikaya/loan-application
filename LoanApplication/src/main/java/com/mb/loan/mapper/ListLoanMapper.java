package com.mb.loan.mapper;

import com.mb.customer.model.Customer;
import com.mb.loan.dto.request.ListLoanDto;
import com.mb.loan.model.Loan;

public class ListLoanMapper {

    public static Loan mapToLoan(ListLoanDto loanInstallment){
        Loan loan = new Loan();
        loan.setPaid(loanInstallment.getPaid());
        loan.setCustomer(new Customer(loanInstallment.getCustomerId()) );
        return  loan;
    }
}
