package com.mb.loan.controller;

import com.mb.auth.dto.response.MessageResponse;
import com.mb.loan.dto.request.CreateLoanDto;
import com.mb.loan.dto.request.ListInstallmentDto;
import com.mb.loan.dto.request.ListLoanDto;
import com.mb.loan.dto.request.PaymentDto;
import com.mb.loan.mapper.*;
import com.mb.loan.model.PaymentResult;
import com.mb.loan.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createLoan(@Valid @RequestBody CreateLoanDto createLoanDto) {
        try {
            return ResponseEntity.ok(CreateLoanDtoMapper.mapToCreatLoanResponseDto(loanService.createLoan(LoanMapper.mapToLoan(createLoanDto),createLoanDto.getInterestRate())) );
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/list")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public ResponseEntity<?> listLoans(@Valid @RequestBody ListLoanDto listLoanDto) {
        try {
            return ResponseEntity.ok(  loanService.listLoans(ListLoanMapper.mapToLoan(listLoanDto)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/listInstalment")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public ResponseEntity<?> listInstalment(@Valid @RequestBody ListInstallmentDto listInstallmentDto) {
        try {
            return ResponseEntity.ok(   loanService.listInstallment(LoanInstallmentMapper.mapToLoanInstallment(listInstallmentDto)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
     }

    @PostMapping("/pay")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public ResponseEntity<?> payLoan(@Valid @RequestBody PaymentDto paymentDto) {
        try {
            PaymentResult result = loanService.pay(paymentDto.getLoanId(),  paymentDto.getAmount());
            return ResponseEntity.ok(PaymentDtoMapper.mapToPaymentResponseDto(result));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
   }

}