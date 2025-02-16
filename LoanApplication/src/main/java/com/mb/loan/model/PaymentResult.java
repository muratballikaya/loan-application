package com.mb.loan.model;

public class PaymentResult {


    private int payedInstallments;

    private int payedInstallmentsSoFar;
    private double totalAmountSpent;
    private double amountPaid;

    private boolean isPaidCompletely;


    public int getPayedInstallments() {
        return payedInstallments;
    }

    public void setPayedInstallments(int payedInstallments) {
        this.payedInstallments = payedInstallments;
    }

    public double getTotalAmountSpent() {
        return totalAmountSpent;
    }

    public void setTotalAmountSpent(double totalAmountSpent) {
        this.totalAmountSpent = totalAmountSpent;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public boolean isPaidCompletely() {
        return isPaidCompletely;
    }

    public void setPaidCompletely(boolean paidCompletely) {
        isPaidCompletely = paidCompletely;
    }

    public int getPayedInstallmentsSoFar() {
        return payedInstallmentsSoFar;
    }

    public void setPayedInstallmentsSoFar(int payedInstallmentsSoFar) {
        this.payedInstallmentsSoFar = payedInstallmentsSoFar;
    }
}
