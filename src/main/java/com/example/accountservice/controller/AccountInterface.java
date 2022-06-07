package com.example.accountservice.controller;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */
public interface AccountInterface {

    ResponseEntity<AccountDto> openAccountForExistingCustomer(
            @PathVariable("customer-id") long customerId,
            @RequestHeader("Initial-Credit") long initialCredit,
            @RequestHeader(value = "X-Request-Id" ,defaultValue = "MOCKREQUESTID") String requestId
    ) throws CustomerNotFoundException;

    ResponseEntity<AccountDto> getAccount(@PathVariable("account-id") Long id) throws AccountNotFoundException;

    ResponseEntity<List<AccountDto>> getAccountList(Pageable pageable);
}
