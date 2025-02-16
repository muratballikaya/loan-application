package com.mb.loan.mapper;

import com.mb.loan.dto.request.CreateLoanDto;
import com.mb.loan.dto.response.CreateLoanResponseDto;
import com.mb.loan.dto.response.LoanInstalmentDto;
import com.mb.loan.model.LoanInstallment;

import java.util.List;
import java.util.stream.Collectors;

public class CreateLoanDtoMapper {


    public static CreateLoanResponseDto mapToCreatLoanResponseDto(List<LoanInstallment> loanInstallments) {
        CreateLoanResponseDto createLoanDto = new CreateLoanResponseDto();
        createLoanDto.setLoanId(loanInstallments.get(0).getLoan().getId());
        createLoanDto.setInstallments(loanInstallments.stream().map(CreateLoanDtoMapper::mapToDto).collect(Collectors.toList()));
        return createLoanDto;
    }
    public static LoanInstalmentDto mapToDto(LoanInstallment loanInstallment) {
        LoanInstalmentDto loanInstalmentDto = new LoanInstalmentDto();
        loanInstalmentDto.setId(loanInstallment.getId());
        loanInstalmentDto.setAmount(loanInstallment.getAmount());
        loanInstalmentDto.setPaidAmount(loanInstallment.getPaidAmount());
        loanInstalmentDto.setDueDate(loanInstallment.getDueDate());
        loanInstalmentDto.setPaymentDate(loanInstallment.getPaymentDate());
        loanInstalmentDto.setPaid(loanInstallment.isPaid());
        loanInstalmentDto.setLoanId(loanInstallment.getLoan().getId());
        return loanInstalmentDto;
    }
}
