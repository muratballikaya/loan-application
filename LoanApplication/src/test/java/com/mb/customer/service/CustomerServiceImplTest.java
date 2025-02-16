package com.mb.customer.service.impl;

import com.mb.auth.dto.request.SignupRequest;
import com.mb.auth.security.services.AuthorizationService;
import com.mb.auth.security.utils.CredentialUtil;
import com.mb.customer.exception.CustomerNotFoundException;
import com.mb.customer.model.Customer;
import com.mb.customer.repository.CustomerRepository;
import com.mb.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        SignupRequest signupRequest = new SignupRequest();
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        doNothing().when(authorizationService).signup(any(SignupRequest.class));

        // Act
        SignupRequest result = customerService.createCustomer(customer);

        // Assert
        assertNotNull(result);
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(authorizationService, times(1)).signup(any(SignupRequest.class));
    }

    @Test
    void testListCustomers() {
        // Arrange
        List<Customer> customers = Collections.singletonList(new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<Customer> result = customerService.listCustomers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindCustomerById() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> result = customerService.findCustomerById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testSaveCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Act
        Customer result = customerService.saveCustomer(customer);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testIncreaseCreditLimit() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsedCreditLimit(500.0);
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Act
        customerService.increaseCreditLimit(1L, 100.0);

        // Assert
        assertEquals(400.0, customer.getUsedCreditLimit());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testIncreaseCreditLimit_CustomerNotFound() {
        // Arrange
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.increaseCreditLimit(1L, 100.0));
    }
}