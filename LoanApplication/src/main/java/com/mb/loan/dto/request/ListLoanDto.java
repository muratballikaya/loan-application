package com.mb.loan.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ListLoanDto {

    private Boolean isPaid;

    private Long customerId;

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
