package com.mb.loan.exception;

public class LoanAlreadyPayedException extends RuntimeException {
    public LoanAlreadyPayedException() {
        super("Loan already payed");
    }
}
