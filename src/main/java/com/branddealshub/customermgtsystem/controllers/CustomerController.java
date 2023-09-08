package com.branddealshub.customermgtsystem.controllers;

import com.branddealshub.customermgtsystem.data.models.Customer;
import com.branddealshub.customermgtsystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")

public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
       List<Customer> allCustomers = customerService.getCustomers();
       return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long theId) {
        Customer theCustomer = customerService.getCustomer(theId);
        return new ResponseEntity<>(theCustomer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer theCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(theCustomer, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer theCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(theCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long theId) {
            customerService.deleteCustomer(theId);
    }


}
