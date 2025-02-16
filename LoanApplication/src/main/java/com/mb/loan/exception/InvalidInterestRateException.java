package com.mb.loan.exception;

public class InvalidInterestRateException extends RuntimeException {
    public InvalidInterestRateException(Float minInterestRate, Float maxInterestRate) {
     super("Interest rate should be between " + minInterestRate + " and " + maxInterestRate);
    }
}
