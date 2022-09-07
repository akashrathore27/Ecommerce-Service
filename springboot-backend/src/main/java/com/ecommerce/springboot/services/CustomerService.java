package com.ecommerce.springboot.services;

import com.ecommerce.springboot.payloads.CustomerDto;

import java.util.List;

public interface CustomerService
{
    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto,Long customerId);
    CustomerDto getCustomerById(Long customerId);

    List<CustomerDto> getAllCustomers();

    void  deleteCustomer(Long customerId);

}
