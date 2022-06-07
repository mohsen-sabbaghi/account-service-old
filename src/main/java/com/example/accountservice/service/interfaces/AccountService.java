package com.example.accountservice.service.interfaces;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.exception.CustomerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */
public interface AccountService {

    AccountDto openAccountForExistingCustomer(long customerId, long initialCredit) throws CustomerNotFoundException;

    AccountDto findOne(Long id);

    Page<AccountDto> findAll(Pageable pageable);
}
