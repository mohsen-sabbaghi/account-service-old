package com.example.accountservice.bootstrap;

import com.example.accountservice.entity.Account;
import com.example.accountservice.entity.Customer;
import com.example.accountservice.entity.TransactionHistory;
import com.example.accountservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

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
        TransactionHistory transactionHistory = new TransactionHistory(1);
        transactionHistory.setReferenceNo(System.currentTimeMillis() / 1000);
        Account account = new Account();
        account.addTransaction(transactionHistory);

        TransactionHistory transactionHistory2 = new TransactionHistory(-1);
        transactionHistory2.setReferenceNo(System.currentTimeMillis() / 1000 + 1);
        account.addTransaction(transactionHistory2);

        TransactionHistory transactionHistory3 = new TransactionHistory(3);
        transactionHistory.setReferenceNo(System.currentTimeMillis() / 1000);
        Account account2 = new Account();
        account2.addTransaction(transactionHistory3);

        Customer customer = new Customer();
        customer.setName("Mohsen");
        customer.setSurname("Sabbaghi");

        customer.addAccount(account);
        customer.addAccount(account2);
        customerRepository.save(customer);
        log.debug("#customer1 : {}", customer);

        Customer customerPaul = new Customer();
        customerPaul.setName("customerName");
        customerPaul.setSurname("customerFamily");
        customerRepository.save(customerPaul);
        log.debug("#customer2 : {}", customerPaul);
    }


}
