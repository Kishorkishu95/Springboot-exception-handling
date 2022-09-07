package com.kishor.ExceptionHandling.controller;

import com.kishor.ExceptionHandling.exception.CustomerAlreadyExistException;
import com.kishor.ExceptionHandling.exception.ErrorResponse;
import com.kishor.ExceptionHandling.model.Customer;
import com.kishor.ExceptionHandling.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        Customer foundCustomer = customerService.getCustomerById(id);
        if (foundCustomer != null) {

            return new ResponseEntity<>(foundCustomer, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customers/addCustomer")
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/customers/updateCustomer")
    public String updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomerById() {
        List<Customer> ls = customerService.getAllCustomers();
        if (ls.isEmpty()) {
            return null;
        } else {
            return ls;
        }
    }
    @DeleteMapping("/customers/{id}")
    public String updateCustomer(@PathVariable("id") Long id) {
        return customerService.deleteCustomer(id);
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCustomerAlreadyExistException(CustomerAlreadyExistException exception){
        return new ErrorResponse(HttpStatus.CONFLICT.value(),exception.getMessage());
    }


}



