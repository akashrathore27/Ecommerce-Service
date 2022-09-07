package com.ecommerce.springboot.controller;

import com.ecommerce.springboot.repository.CustomerReporsitory;
import com.ecommerce.springboot.services.CustomerService;
import com.ecommerce.springboot.payloads.ApiResponse;
import com.ecommerce.springboot.payloads.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class CustomerController
{

    @Autowired
    private CustomerReporsitory customerReporsitory;

    @Autowired
    private CustomerService customerService;

//    Post Api For Add customer
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@Valid  @RequestBody CustomerDto customerDto)
    {
        CustomerDto createCustomerDto=this.customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createCustomerDto, HttpStatus.CREATED);
    }

//    Put Api for Delete Customer
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto,@PathVariable Long customerId){

        CustomerDto updatedCustomer =	this.customerService.updateCustomer(customerDto, customerId);
        return ResponseEntity.ok(updatedCustomer);
    }

    //Delete  Api for  delete customer
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Long customerId){
        this.customerService.deleteCustomer(customerId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Customer deleted Successfully",true), HttpStatus.OK);

    }

    //Get Api for get All customers
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(this.customerService.getAllCustomers());
    }

    //Get api for get customer by id
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDto> getSingleCustomer(@PathVariable Long customerId){
        return ResponseEntity.ok(this.customerService.getCustomerById(customerId));

    }


}
