package com.kishor.ExceptionHandling.service;

import com.kishor.ExceptionHandling.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);
    String addCustomer(Customer customer);
    String updateCustomer(Customer customer);
    List<Customer> getAllCustomers();

    String deleteCustomer(Long id);
}
