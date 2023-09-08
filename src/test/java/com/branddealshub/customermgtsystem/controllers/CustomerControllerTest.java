package com.branddealshub.customermgtsystem.controllers;

import com.branddealshub.customermgtsystem.data.models.Customer;
import com.branddealshub.customermgtsystem.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;

@SpringJUnitConfig
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private List<Customer> customers = Collections.singletonList(
            new Customer(1L, "John", "Doe", "johndoe@example.com", "1234567890", "USA", "New York", "123 Main Street"));

    @Test
    void testGetCustomers() throws Exception {
        when(customerService.getCustomers()).thenReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(customers.size()));
    }

    @Test
    void testGetCustomer() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer(1L, "John", "Doe", "johndoe@example.com", "1234567890", "USA", "New York", "123 Main Street");
        when(customerService.getCustomer(customerId)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(customer.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(customer.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(customer.getEmail()));
    }

    @Test
    void testAddCustomer() throws Exception {
        Customer newCustomer = new Customer(null, "Jane", "Doe", "janedoe@example.com", "9876543210", "USA", "Chicago", "456 Main Street");
        Customer savedCustomer = new Customer(2L, "Jane", "Doe", "janedoe@example.com", "9876543210", "USA", "Chicago", "456 Main Street");
        when(customerService.addCustomer(newCustomer)).thenReturn(savedCustomer);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .content("{\n" +
                                "  \"firstName\": \"Jane\",\n" +
                                "  \"lastName\": \"Doe\",\n" +
                                "  \"email\": \"janedoe@example.com\",\n" +
                                "  \"phone\": \"9876543210\",\n" +
                                "  \"country\": \"USA\",\n" +
                                "  \"city\": \"Chicago\",\n" +
                                "  \"address\": \"456 Main Street\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedCustomer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(savedCustomer.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(savedCustomer.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(savedCustomer.getEmail()));
    }




    @Test
    void testUpdateCustomer() throws Exception {
        Customer existingCustomer = new Customer(1L, "John", "Doe", "johndoe@example.com", "1234567890", "USA", "New York", "123 Main Street");
        Customer updatedCustomer = new Customer(1L, "John", "Doe", "johndoe@example.com", "9876543210", "USA", "Chicago", "456 Main Street");
        when(customerService.addCustomer(existingCustomer)).thenReturn(updatedCustomer);

        // Convert the Customer object to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(existingCustomer);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedCustomer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(updatedCustomer.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(updatedCustomer.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(updatedCustomer.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(updatedCustomer.getPhone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country").value(updatedCustomer.getCountry()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(updatedCustomer.getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(updatedCustomer.getAddress()));
    }


    @Test
    void testDeleteCustomer() throws Exception {
        Long customerId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/{id}", customerId))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}