package com.kishor.ExceptionHandling.dao;

import com.kishor.ExceptionHandling.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
