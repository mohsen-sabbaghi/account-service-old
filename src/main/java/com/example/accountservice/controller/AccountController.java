package com.example.accountservice.controller;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.service.interfaces.AccountServiceInterface;
import com.example.accountservice.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private static Map<String,String> idempotenceCache=new ConcurrentHashMap<>();

    @Value("${server.port}")
    private int serverPort;

    public AccountController(AccountServiceInterface accountServiceInterface) {
        this.accountServiceInterface = accountServiceInterface;
    }

    @PostMapping("/customers/{customer-id}/accounts")
    public ResponseEntity<AccountDto> createAccount(@PathVariable("customer-id") long customerId,
                                                    @RequestHeader("Initial-Credit") long initCredit,
                                                    @RequestHeader(value = "X-Request-Id", defaultValue = "123456") String requestId)
            throws ResponseStatusException {
        if (initCredit < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Initial Credit Must be grater than 0");
        if (idempotenceCache.containsKey(requestId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicated Request");
        AccountDto accountDto = accountServiceInterface.createAccount(customerId, initCredit);
        idempotenceCache.put(requestId, String.valueOf(accountDto.getId()));
        URI location = URI.create("http://localhost:" + serverPort + "/v1/accounts/" + accountDto.getId());
        return ResponseEntity.created(location).body(accountDto);
    }

    @GetMapping("/accounts/{account-id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("account-id") Long id) throws ResponseStatusException {
        try {
            return ResponseEntity.ok().body(accountServiceInterface.findById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find account with ID= "+id, e);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccountList(Pageable pageable) {
        Page<AccountDto> page = accountServiceInterface.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
