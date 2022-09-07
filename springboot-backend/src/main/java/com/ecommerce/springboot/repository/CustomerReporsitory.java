package com.ecommerce.springboot.repository;

import com.ecommerce.springboot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReporsitory extends JpaRepository<Customer,Long>
{

//    Optional<Customer> findByEmail(String email);
}
