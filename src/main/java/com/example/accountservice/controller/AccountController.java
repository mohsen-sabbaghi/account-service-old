package com.example.accountservice.controller;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.interceptor.PreventDuplication;
import com.example.accountservice.service.interfaces.AccountServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */

@RestController
@RequestMapping("/v1")
@Slf4j
public class AccountController {
    private final AccountServiceInterface accountServiceInterface;
    @Value("${server.port}")
    private int serverPort;

    public AccountController(AccountServiceInterface accountServiceInterface) {
        this.accountServiceInterface = accountServiceInterface;
    }

    @PostMapping("/customers/{customer-id}/accounts")
    @PreventDuplication
    public ResponseEntity<AccountDto> createAccount(
            @PathVariable("customer-id") long customerId,
            @RequestHeader("Initial-Credit") long initCredit,
            @RequestHeader(value = "Track-Id") String trackId)
            throws ResponseStatusException {
        if (initCredit < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Initial Credit Must be grater than 0");
        AccountDto accountDto = accountServiceInterface.createAccount(customerId, initCredit);
        URI location = URI.create("http://localhost:" + serverPort + "/v1/accounts/" + accountDto.getId());
        return ResponseEntity.created(location).body(accountDto);
    }

    @GetMapping("/accounts/{account-id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("account-id") Long id) throws ResponseStatusException {
        try {
            return ResponseEntity.ok().body(accountServiceInterface.findById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find account with ID= " + id, e.getCause());
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccountList(Pageable pageable) throws ResponseStatusException {
        try {
            Page<AccountDto> page = accountServiceInterface.findAll(pageable);
            return ResponseEntity.ok().body(page.getContent());
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No accounts", e.getCause());
        }

    }
}
