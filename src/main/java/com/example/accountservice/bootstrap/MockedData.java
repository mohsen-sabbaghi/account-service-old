package com.example.accountservice.bootstrap;

import com.example.accountservice.entity.Account;
import com.example.accountservice.entity.Customer;
import com.example.accountservice.entity.TransactionHistory;
import com.example.accountservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

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
    public void run(String... args) {

        TransactionHistory transactionHistory = new TransactionHistory(1000);
        transactionHistory.setTrackNo(System.currentTimeMillis() / 1000);
        Account mohsenAccount = new Account();
        mohsenAccount.setAccountNumber(new Random().nextInt(99999999));
        mohsenAccount.addTransaction(transactionHistory);

        TransactionHistory transactionHistory2 = new TransactionHistory(-100);
        transactionHistory2.setTrackNo((System.currentTimeMillis() / 1000) + 20);
        mohsenAccount.addTransaction(transactionHistory2);

        TransactionHistory transactionHistory3 = new TransactionHistory(3000);
        transactionHistory.setTrackNo(System.currentTimeMillis() / 1000);
        Account johnnyDeppAccount = new Account();
        johnnyDeppAccount.setAccountNumber(new Random().nextInt(99999999));
        johnnyDeppAccount.addTransaction(transactionHistory3);

        Customer mohsenAsCustomer = new Customer();
        mohsenAsCustomer.setName("Mohsen");
        mohsenAsCustomer.setSurname("Sabbaghi");

        mohsenAsCustomer.addAccount(mohsenAccount);
        mohsenAsCustomer.addAccount(johnnyDeppAccount);
        customerRepository.save(mohsenAsCustomer);
        log.debug("#customer1 : {}", mohsenAsCustomer);

        Customer johnnyAsCustomer = new Customer();
        johnnyAsCustomer.setName("Johnny");
        johnnyAsCustomer.setSurname("Depp");
        customerRepository.save(johnnyAsCustomer);
        log.debug("#customer2 : {}", johnnyAsCustomer);


    }


}
