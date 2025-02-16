package com.mb.loan.service.impl;

import com.mb.customer.model.Customer;
import com.mb.customer.service.CustomerService;
import com.mb.loan.exception.*;
import com.mb.loan.model.Loan;
import com.mb.loan.model.LoanInstallment;
import com.mb.loan.model.PaymentResult;
import com.mb.loan.repository.LoanInstalmentRepository;
import com.mb.loan.repository.LoanRepository;
import com.mb.loan.service.LoanService;
import com.mb.loan.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    LoanInstalmentRepository loanInstalmentRepository;

    @Value("${maxNumberOfPayments}")
    private int maxNumberOfPayments;

    @Value("${allowedNumberOfInstallments}")
    private String[] allowedNumberOfInstallments;

    @Value("${minInterestRate}")
    private Float minInterestRate;

    @Value("${maxInterestRate}")
    private Float maxInterestRate;


    @Override
    @Transactional
    public List<LoanInstallment> createLoan(Loan loan, Float interestRate) {
        Optional<Customer> customerOpt = Optional.ofNullable(customerService.findCustomerById(loan.getCustomer().getId()).orElseThrow(UserNotFoundException::new));
        Customer customer = customerOpt.get();
        if((customer.getCreditLimit()-customer.getUsedCreditLimit() < loan.getLoanAmount())){
            throw new NotEnoughCreditLimitException();
        }
        final Integer numberOfInstallment = loan.getNumberOfInstalment();
        if(!Arrays.stream(allowedNumberOfInstallments).anyMatch( s-> s.equals(numberOfInstallment.toString()))){
            throw new InvalidNumberOfInstallmentException(allowedNumberOfInstallments);
        }
        if(interestRate<minInterestRate || interestRate>maxInterestRate){
            throw new InvalidInterestRateException(minInterestRate,maxInterestRate);
        }
        // float min max interest rate validator koyulacak

        Double totalAmount = loan.getLoanAmount() * (1 + Float.parseFloat(interestRate.toString()));
        Double installment = totalAmount/loan.getNumberOfInstalment();

        loan = loanRepository.save(loan);
        List<LoanInstallment> loanInstallmentList = new ArrayList<>();
        Date dateToBeCalculated = new Date();
        for( int i=0;i< loan.getNumberOfInstalment();i++){
            LoanInstallment loanInstallment = new LoanInstallment();
            loanInstallment.setLoan(loan);
            loanInstallment.setAmount(installment);
            loanInstallment.setPaid(false);
            dateToBeCalculated =  DateUtils.calculateFirstDayOfNextMonth(dateToBeCalculated);
            loanInstallment.setDueDate(dateToBeCalculated);
            loanInstallment.setPaidAmount(0d);
            loanInstallmentList.add(loanInstallment);
        }
        customer.setUsedCreditLimit(customer.getUsedCreditLimit()+loan.getLoanAmount());
        customerService.saveCustomer(customer);

        loanInstalmentRepository.saveAll(loanInstallmentList);


        return loanInstallmentList;


    }

    private Date calcualateDueDate(Date createDate) {
        return new Date();
    }

    @Override
    public List<Loan> listLoans(Loan exampleLoan) {
        return loanRepository.findAll(Example.of(exampleLoan));
    }

    @Override
    public List<LoanInstallment> listInstallment(LoanInstallment listInstallment) {
        return loanInstalmentRepository.findAll(Example.of( listInstallment));
    }

    @Override
    @Transactional
    public PaymentResult pay(Long loanId, Double amount) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        Loan loan = loanOpt.orElseThrow(LoanNotFoundException::new);
        if(loan.isPaid()){
            throw new LoanAlreadyPayedException();
        }
        List<LoanInstallment> loanInstallmentList = loanInstalmentRepository.findByLoanAndIsPaid(loan,false);
        Double installmentValue;
        PaymentResult paymentResult = new PaymentResult();

        List<LoanInstallment> installmentsToBePayed = new ArrayList<>();
        List<LoanInstallment> installmentsCouldNotBePayed = new ArrayList<>();
        for(LoanInstallment loanInstallment : loanInstallmentList){
              installmentValue = loanInstallment.getAmount();
              if( installmentValue>amount ){
                  if(CollectionUtils.isEmpty(installmentsToBePayed) ){
                      throw new AmountIsNotEnoughToBePayedException();
                  }
                    installmentsCouldNotBePayed.add(loanInstallment);
                  continue;
              }
            if(installmentsToBePayed.size()>=maxNumberOfPayments){
                installmentsCouldNotBePayed.add(loanInstallment);
                continue;
            }
            installmentsToBePayed.add(loanInstallment);
              amount-=installmentValue;
              paymentResult.setAmountPaid(paymentResult.getAmountPaid()+installmentValue);
              paymentResult.setPayedInstallments(paymentResult.getPayedInstallments()+1);


        }

        for(LoanInstallment loanInstallment : installmentsToBePayed){
            loanInstallment.setPaid(true);
            loanInstallment.setPaidAmount(loanInstallment.getAmount());
        }

        loanInstalmentRepository.saveAll(loanInstallmentList);
        if(installmentsToBePayed.size() == loanInstallmentList.size()){
            loan.setPaid(true);
            paymentResult.setPaidCompletely(true);
            loanRepository.save(loan);
            customerService.increaseCreditLimit(loan.getCustomer().getId(),loan.getLoanAmount());
        }

      paymentResult.setPayedInstallmentsSoFar(loan.getNumberOfInstalment() - installmentsCouldNotBePayed.size());
      paymentResult.setTotalAmountSpent(installmentsCouldNotBePayed.stream().mapToDouble(LoanInstallment::getAmount).sum());

      return paymentResult;


    }
}
