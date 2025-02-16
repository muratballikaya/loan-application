package com.mb.loan.exception;

public class NotEnoughCreditLimitException extends  RuntimeException{
    public NotEnoughCreditLimitException(){
        super("There is not enough credit limit to be applied!");
    }

}
