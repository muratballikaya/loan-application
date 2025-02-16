package com.mb.loan.mapper;

import com.mb.customer.model.Customer;
import com.mb.loan.dto.request.CreateLoanDto;
import com.mb.loan.model.Loan;

import java.util.Date;

public class LoanMapper {

    public static Loan mapToLoan(CreateLoanDto dto){

        Loan loan = new Loan();
        loan.setLoanAmount(dto.getAmount());
        loan.setCustomer(new Customer(dto.getCustomerId()));
        loan.setCreateDate(new Date());
        loan.setPaid(false);
        loan.setNumberOfInstalment(dto.getNumberOfInstallments());

        return loan;
    }
}
