package com.mb.customer.mapper;

import com.mb.customer.dto.request.CustomerDto;
import com.mb.customer.model.Customer;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto ){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setCreditLimit(customerDto.getCreditLimit());
        customer.setSurname(customerDto.getSurname());
        customer.setUsedCreditLimit(customerDto.getUsedCreditLimit());
        return customer;
    }
}
