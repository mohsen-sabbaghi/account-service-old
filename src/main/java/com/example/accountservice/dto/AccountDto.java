package com.example.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/7/2022
 */

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    private Long id;

    @JsonProperty("created_at")
    private Date createdAt;

    @NotNull(message = "can not be null")
    private Long balance;

    @JsonProperty("transactions")
    private Set<AccountTransactionDto> accountTransactions;
}
