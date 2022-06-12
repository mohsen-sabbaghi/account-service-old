package com.example.accountservice.service;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.entity.Account;
import com.example.accountservice.entity.Customer;
import com.example.accountservice.entity.TransactionHistory;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.repository.CustomerRepository;
import com.example.accountservice.service.interfaces.AccountServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */

@Service
@Transactional
@Slf4j
public class AccountServiceInterfaceImpl implements AccountServiceInterface {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public AccountServiceInterfaceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(long customerId, long initCredit) throws ResponseStatusException {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Account account = new Account(customer);
            if (initCredit > 0) {
                TransactionHistory transactionHistory = new TransactionHistory(initCredit);
                transactionHistory.setReferenceNo(System.currentTimeMillis() / 1000);
                account.addTransaction(transactionHistory);
            } else {
                account.setBalance(initCredit);
            }
            accountRepository.save(account);
            return new ModelMapper().map(account, AccountDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer Not Found");
        }
    }

    @Override
    public AccountDto findById(Long id)   {
        return accountRepository.findById(id)
                .map(account -> new ModelMapper().map(account, AccountDto.class)).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Not Found"));
    }

    @Override
    public Page<AccountDto> findAll(Pageable pageable) {
        log.debug("Request to get all Account");
        return accountRepository.findAll(pageable).map(account -> new ModelMapper().map(account, AccountDto.class));
    }
}
