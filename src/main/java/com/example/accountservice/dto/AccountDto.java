package com.example.accountservice.dto;

import com.example.accountservice.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    private Long id;

    @JsonProperty("created_time")
    private Date createdTime;

    @NotNull(message = "can not be null")
    private Long accountNumber;

    @NotNull(message = "can not be null")
    private Long balance;

    @NotNull(message = "can not be null")
    private String accountOwner;

    @JsonProperty("transactions")
    private Set<AccountTransactionDto> accountTransactions;
}
