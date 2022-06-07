package com.example.accountservice.service;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.exception.CustomerNotFoundException;
import com.example.accountservice.service.interfaces.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountDto openAccountForExistingCustomer(long customerId, long initialCredit) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public AccountDto findOne(Long id) {
        return null;
    }

    @Override
    public Page<AccountDto> findAll(Pageable pageable) {
        return null;
    }
}
