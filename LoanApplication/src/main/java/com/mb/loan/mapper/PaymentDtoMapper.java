package com.mb.loan.mapper;

import com.mb.loan.dto.response.PaymentResponseDto;
import com.mb.loan.model.PaymentResult;

public class PaymentDtoMapper {
    public static PaymentResponseDto mapToPaymentResponseDto(PaymentResult result) {
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setPayedInstallments(result.getPayedInstallments());
        paymentResponseDto.setTotalAmountSpent(result.getTotalAmountSpent());
        paymentResponseDto.setAmountPaid(result.getAmountPaid());
        paymentResponseDto.setPaidCompletely(result.isPaidCompletely());
        return paymentResponseDto;
    }
}
