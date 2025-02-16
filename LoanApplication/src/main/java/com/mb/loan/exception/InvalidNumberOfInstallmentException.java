package com.mb.loan.exception;

public class InvalidNumberOfInstallmentException extends RuntimeException{
    public InvalidNumberOfInstallmentException(String[] allowedNumberOfInstallments) {
        super("Invalid number of installment. Allowed values are: " + String.join(",", allowedNumberOfInstallments));
    }
}
