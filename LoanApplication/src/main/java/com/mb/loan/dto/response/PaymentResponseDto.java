package com.mb.loan.dto.response;

public class PaymentResponseDto {

    private int payedInstallments;
    private Double totalAmountSpent;
    private Double amountPaid;

    private boolean isPaidCompletely;


    public int getPayedInstallments() {
        return payedInstallments;
    }

    public void setPayedInstallments(int payedInstallments) {
        this.payedInstallments = payedInstallments;
    }

    public Double getTotalAmountSpent() {
        return totalAmountSpent;
    }

    public void setTotalAmountSpent(Double totalAmountSpent) {
        this.totalAmountSpent = totalAmountSpent;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public boolean isPaidCompletely() {
        return isPaidCompletely;
    }

    public void setPaidCompletely(boolean paidCompletely) {
        isPaidCompletely = paidCompletely;
    }
}
