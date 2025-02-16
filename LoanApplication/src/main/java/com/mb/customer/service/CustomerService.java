package com.mb.customer.service;

import com.mb.auth.dto.request.SignupRequest;
import com.mb.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    SignupRequest createCustomer(Customer customer);

    List<Customer> listCustomers();

    Optional<Customer> findCustomerById(Long id);

    Customer saveCustomer(Customer customer);

    void increaseCreditLimit(Long id, Double amount);
}
