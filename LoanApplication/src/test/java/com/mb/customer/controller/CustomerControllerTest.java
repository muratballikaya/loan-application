package com.mb.customer.controller;

import com.mb.auth.dto.request.SignupRequest;
import com.mb.customer.dto.request.CustomerDto;
import com.mb.customer.model.Customer;
import com.mb.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        CustomerDto createCustomerDto = new CustomerDto();
        createCustomerDto.setName("Ali veli");
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Ali veli");

        SignupRequest signupRequest = new SignupRequest();
        when(customerService.createCustomer(any(Customer.class))).thenReturn(signupRequest);

        // Act
        ResponseEntity<?> response = customerController.createCustomer(createCustomerDto);

        // Assert
        assertEquals(signupRequest, response.getBody());
    }

}