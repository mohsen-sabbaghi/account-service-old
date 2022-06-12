package com.example.accountservice.controller;

import com.example.accountservice.dto.AccountDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */
public interface AccountInterface {

    ResponseEntity<AccountDto> createAccount(
            @PathVariable("customer-id") long customerId,
            @RequestHeader("Initial-Credit") long initCredit,
            @RequestHeader(value = "X-Request-Id", defaultValue = "123456") String requestId
    ) throws ResponseStatusException;

    ResponseEntity<AccountDto> getAccount(@PathVariable("account-id") Long id) throws ResponseStatusException;

    ResponseEntity<List<AccountDto>> getAccountList(Pageable pageable);
}
