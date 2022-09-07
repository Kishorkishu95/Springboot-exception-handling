package com.kishor.ExceptionHandling.service;

import com.kishor.ExceptionHandling.dao.CustomerRepository;
import com.kishor.ExceptionHandling.exception.CustomerAlreadyExistException;
import com.kishor.ExceptionHandling.exception.NoSuchCustomerExistException;
import com.kishor.ExceptionHandling.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NoSuchCustomerExistException("There is no customer with the ID " + id + " provided."));
    }

    @Override
    public String addCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
        if (existingCustomer == null) {
            customerRepository.save(customer);
            return "Customer added successfully.";
        } else {
            throw new CustomerAlreadyExistException("Customer is already exist");
        }
    }

    @Override
    public String updateCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
        if (existingCustomer == null) {
            throw new NoSuchCustomerExistException("No such customer exists.");
        } else {
            existingCustomer.setName(customer.getName());
            existingCustomer.setAddress(customer.getAddress());
            customerRepository.save(existingCustomer);
            return "Customer updated successfully.";
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public String deleteCustomer(Long id) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if(existingCustomer!=null){
            customerRepository.delete(existingCustomer);
            return "Customer with the id "+id+" has been deleted successfully";
        }
        else{
            throw new NoSuchCustomerExistException("No Customer found for the provided id.");
        }

    }
}
