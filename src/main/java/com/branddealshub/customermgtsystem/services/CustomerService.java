package com.branddealshub.customermgtsystem.services;

import com.branddealshub.customermgtsystem.data.models.Customer;
import com.branddealshub.customermgtsystem.data.repositories.CustomerRepository;
import com.branddealshub.customermgtsystem.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    public Customer getCustomer(Long theId) {
        return customerRepository.findById(theId)
                .orElseThrow(() ->
                        new CustomerNotFoundException("The required customer was not "));
    }
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public Customer updateCustomerById(Long theId, Customer updatedCustomer) {

        return customerRepository.findById(theId)
                .map(existingCustomer -> {
                  existingCustomer.setFirstName(updatedCustomer.getFirstName());
                  existingCustomer.setLastName(updatedCustomer.getLastName());
                  existingCustomer.setEmail(updatedCustomer.getEmail());
                  existingCustomer.setPhone(updatedCustomer.getPhone());
                  existingCustomer.setCountry(updatedCustomer.getCountry());
                  existingCustomer.setCity(updatedCustomer.getCity());
                  existingCustomer.setAddress(updatedCustomer.getAddress());

    return customerRepository.save(existingCustomer);
            })
            .orElseThrow(() ->
                    new CustomerNotFoundException("Customer with ID " + theId + " not found"));
    }

   public void deleteCustomer(Long theId) {
        customerRepository.deleteById(theId);
    }
}
