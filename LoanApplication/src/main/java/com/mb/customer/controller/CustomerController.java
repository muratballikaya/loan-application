package com.mb.customer.controller;

import com.mb.auth.dto.response.MessageResponse;
import com.mb.auth.dto.response.Token;
import com.mb.customer.dto.request.CustomerDto;
import com.mb.customer.service.CustomerService;
import com.mb.customer.mapper.CustomerMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        try {
            return ResponseEntity.ok(customerService.createCustomer(CustomerMapper.mapToCustomer(customerDto)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listCustomers() {
        try {
            return ResponseEntity.ok(customerService.listCustomers());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

    }

}
