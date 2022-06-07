package com.example.accountservice.controller;

import com.example.accountservice.dto.CustomerDto;
import com.example.accountservice.exception.CustomerNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */
public interface CustomerInterface {

    @GetMapping("/customers")
    ResponseEntity<List<CustomerDto>> getCustomersList(Pageable pageable);

    ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customer-id") long customerId) throws CustomerNotFoundException;
}
