package com.example.accountservice.controller;

import com.example.accountservice.dto.CustomerDto;
import com.example.accountservice.exception.CustomerNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */
public interface CustomerInterface {

    @GetMapping("/customers")
    ResponseEntity<List<CustomerDto>> getCustomersList(Pageable pageable);

    @GetMapping("/customers/{customer-id}")
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customer-id") long customerId) throws CustomerNotFoundException;

    @PostMapping("/customers")
    ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) throws URISyntaxException;
}
