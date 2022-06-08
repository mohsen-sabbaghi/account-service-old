package com.example.accountservice.bootstrap;

import com.example.accountservice.entity.Account;
import com.example.accountservice.entity.AccountTransaction;
import com.example.accountservice.entity.Customer;
import com.example.accountservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/8/2022
 */
@Configuration
@Slf4j
public class MockedData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public MockedData(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        AccountTransaction accountTransaction = new AccountTransaction(1);
        accountTransaction.setReferenceNo(Instant.now().toEpochMilli() / 1000);
        Account account = new Account();
        account.addTransaction(accountTransaction);

        AccountTransaction accountTransaction2 = new AccountTransaction(-1);
        accountTransaction2.setReferenceNo(Instant.now().toEpochMilli() / 1000 + 1);
        account.addTransaction(accountTransaction2);

        AccountTransaction accountTransaction3 = new AccountTransaction(3);
        accountTransaction.setReferenceNo(Instant.now().toEpochMilli() / 1000);
        Account account2 = new Account();
        account2.addTransaction(accountTransaction3);

        Customer customer = new Customer();
        customer.setName("Mohsen");
        customer.setSurname("Sabbaghi");

        customer.addAccount(account);
        customer.addAccount(account2);
        customerRepository.save(customer);
        log.debug("#customer1 : {}", customer);

        Customer customerPaul = new Customer();
        customerPaul.setName("Peter");
        customerPaul.setSurname("Paul");
        customerRepository.save(customerPaul);
        log.debug("#customer2 : {}", customerPaul);
    }


}
