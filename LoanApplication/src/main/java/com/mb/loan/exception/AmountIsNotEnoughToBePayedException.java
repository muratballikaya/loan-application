package com.mb.loan.exception;

public class AmountIsNotEnoughToBePayedException extends RuntimeException {
    public AmountIsNotEnoughToBePayedException() {
        super("Amount is not enough to be payed");
    }
}
