package com.mb.loan.model;

import com.mb.customer.model.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loan")
public class Loan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "loan")
    List<LoanInstallment> loanInstallmentList;

    @ManyToOne
    @JoinColumn(name="customer", referencedColumnName = "id")
     private Customer customer;

    private
    Double loanAmount;

    private
    Integer numberOfInstalment;


    private
    Date createDate;


    Boolean isPaid;

    public Loan(Long id){
        this.id=id;
    }

    public Loan(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getNumberOfInstalment() {
        return numberOfInstalment;
    }

    public void setNumberOfInstalment(Integer numberOfInstalment) {
        this.numberOfInstalment = numberOfInstalment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
