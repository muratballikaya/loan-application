package com.mb.loan.dto.response;


import java.util.List;

public class CreateLoanResponseDto {

    private long loanId;
    private List<LoanInstalmentDto> installments;

    public List<LoanInstalmentDto> getInstallments() {
        return installments;
    }

    public void setInstallments(List<LoanInstalmentDto> installments) {
        this.installments = installments;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public long getLoanId() {
        return loanId;
    }
}
