package com.branddealshub.customermgtsystem.services;

import com.branddealshub.customermgtsystem.data.models.Customer;
import com.branddealshub.customermgtsystem.data.repositories.CustomerRepository;
import com.branddealshub.customermgtsystem.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomers() {
        // Arrange
        List<Customer> expectedCustomers = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        // Act
        List<Customer> actualCustomers = customerService.getCustomers();

        // Assert
        assertSame(expectedCustomers, actualCustomers);
    }

    @Test
    void testGetCustomer() {
        // Arrange
        Long customerId = 1L;
        Customer expectedCustomer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(expectedCustomer));

        // Act
        Customer actualCustomer = customerService.getCustomer(customerId);

        // Assert
        assertSame(expectedCustomer, actualCustomer);
    }

    @Test
    void testGetCustomerNotFound() {
        // Arrange
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(customerId));
    }

    @Test
    void testAddCustomer() {
        // Arrange
        Customer customerToAdd = new Customer();
        when(customerRepository.save(customerToAdd)).thenReturn(customerToAdd);

        // Act
        Customer addedCustomer = customerService.addCustomer(customerToAdd);

        // Assert
        assertSame(customerToAdd, addedCustomer);
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        Long customerId = 1L;

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(customerRepository, times(1)).deleteById(customerId);
    }
}