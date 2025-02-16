package com.mb.loan.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ListInstallmentDto {

    private Long loanId;
    private Boolean isPaid;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
