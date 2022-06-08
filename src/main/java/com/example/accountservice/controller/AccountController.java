package com.example.accountservice.controller;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.exception.CustomerNotFoundException;
import com.example.accountservice.service.interfaces.AccountService;
import com.example.accountservice.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */

@RestController
@RequestMapping("/v1")
@Slf4j
public class AccountController implements AccountInterface {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<AccountDto> openAccountForExistingCustomer(long customerId, long initialCredit, String requestId) throws CustomerNotFoundException {
        return null;
    }

    @Override
    @GetMapping("/accounts/{account-id}")
    public ResponseEntity<AccountDto> getAccount(Long id) throws AccountNotFoundException {
        log.debug("REST request to get Account : {}", id);
        return ResponseEntity.ok().body(accountService.findOne(id));
    }


    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccountList(Pageable pageable) {
        System.out.println("pageable = " + pageable);
        log.debug("REST request to get a page of Accounts");
        Page<AccountDto> page = accountService.findAll(pageable);
        System.err.println("page: "+page.getTotalPages());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
