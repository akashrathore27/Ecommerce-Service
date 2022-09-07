package com.ecommerce.springboot.services.impl;

import com.ecommerce.springboot.model.Customer;
import com.ecommerce.springboot.repository.CustomerReporsitory;
import com.ecommerce.springboot.services.CustomerService;
import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.payloads.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService
{

    @Autowired
    private CustomerReporsitory customerReporsitory;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {

        Customer customer=this.dtoToCustomer(customerDto);
        Customer savedCustomer=this.customerReporsitory.save(customer);
        return this.customerToDto(savedCustomer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long customerId) {

        Customer customer=this.customerReporsitory.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer ","Customer Id",customerId));

        customer.setCustomerName(customerDto.getCustomerName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());

        Customer updatedCustomer=this.customerReporsitory.save(customer);
        CustomerDto customerDto1=this.customerToDto(updatedCustomer);

        return customerDto1;
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {

        Customer customer=this.customerReporsitory.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer ","Customer Id",customerId));



        return this.customerToDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer>customers=this.customerReporsitory.findAll();
        List<CustomerDto>customerDtos= customers.stream().map(customer -> this.customerToDto(customer)).collect(Collectors.toList());

        return customerDtos;
    }

    @Override
    public void deleteCustomer(Long customerId) {

        Customer customer=this.customerReporsitory.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer ","Customer Id",customerId));

          this.customerReporsitory.delete(customer);
    }

    private Customer dtoToCustomer(CustomerDto customerDto)
    {
        Customer customer=this.modelMapper.map(customerDto,Customer.class);


        return customer;
    }
    public CustomerDto customerToDto(Customer customer)
    {
        CustomerDto customerDto=this.modelMapper.map(customer,CustomerDto.class);


        return  customerDto;
    }


}
