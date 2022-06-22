package com.example.accountservice.controller;

import com.example.accountservice.dto.AccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {

    @Autowired
    AccountController accountController;
    @LocalServerPort
    int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    @Test
    void contextLoads() {
        assertNotNull(accountController);
        assertNotNull(restTemplate);
    }

    @Test
    void createAccountForExistingCustomer() {
        String existingCustomerId = "1";
        String resourceUrl = "http://localhost:" + port + "/v1/customers/" + existingCustomerId + "/accounts";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Initial-Credit", "1000");
        headers.add("Track-Id", String.valueOf(System.currentTimeMillis()));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<AccountDto> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.POST, entity, AccountDto.class);
        AccountDto accountDto = responseEntity.getBody();
        System.err.println("accountDto" + accountDto);
        System.err.println("accountDto.getAccountTransactions().size()" + accountDto.getAccountTransactions().size());
        assertEquals(1000, accountDto.getBalance());
        assertEquals(1, accountDto.getAccountTransactions().size());
    }

    @Test
    void getAccount() {
    }

    @Test
    void getAccountList() {
    }
}