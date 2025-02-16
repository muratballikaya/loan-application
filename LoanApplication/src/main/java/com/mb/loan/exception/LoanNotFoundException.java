package com.mb.loan.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException() {
        super("There is no loan to be payed");
    }
}
