package com.example.accountservice.controller;

import com.example.accountservice.dto.CustomerDto;
import com.example.accountservice.exception.CustomerNotFoundException;
import com.example.accountservice.service.interfaces.CustomerServiceInterface;
import com.example.accountservice.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/11/2022
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class CustomerController implements CustomerInterface {
    private final CustomerServiceInterface customerServiceInterface;

    public CustomerController(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getCustomersList(Pageable pageable) {
        Page<CustomerDto> page = customerServiceInterface.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomerById(long customerId) throws CustomerNotFoundException {
        return ResponseEntity.ok().body(customerServiceInterface.findById(customerId));
    }

    @Override
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto) throws URISyntaxException {
        return null;
    }
}
