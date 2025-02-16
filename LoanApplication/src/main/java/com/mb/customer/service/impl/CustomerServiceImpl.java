package com.mb.customer.service.impl;

import com.mb.auth.dto.request.SignupRequest;
import com.mb.auth.security.services.AuthorizationService;
import com.mb.auth.security.utils.CredentialUtil;
import com.mb.customer.exception.CustomerNotFoundException;
import com.mb.customer.model.Customer;
import com.mb.customer.repository.CustomerRepository;
import com.mb.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthorizationService authorizationService;


    @Override
    @Transactional
    public SignupRequest createCustomer(Customer customer){
        customer = customerRepository.save(customer);

        SignupRequest signupRequest= new SignupRequest();
        signupRequest.setUsername(String.valueOf(customer.getId()));
        signupRequest.setPassword(CredentialUtil.generatePassword());
        authorizationService.signup(signupRequest);

        return  signupRequest;
    }

    @Override
    public List<Customer> listCustomers(){
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
       return customerRepository.findById(id);
    }

    @Override
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    @Override
    public void increaseCreditLimit(Long id, Double amount){
        Optional<Customer> customerOpt = findCustomerById(id);
        Customer customer = customerOpt.orElseThrow(CustomerNotFoundException::new);
        customer.setUsedCreditLimit(customer.getUsedCreditLimit()-amount);
        saveCustomer(customer);
    }

}
