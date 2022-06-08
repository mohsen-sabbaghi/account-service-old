package com.example.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/8/2022
 */

@Entity
@Table(name = "account_transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @NotNull
    @Column(name = "amount", nullable = false)
    private long amount;

    @NotNull
    @Column(name = "new_balance", nullable = false)
    private long newBalance;

    @Column(name = "reference_no")
    private Long referenceNo;
    @ManyToOne
    @JoinColumn(name = "fk_account")
    private Account account;

    public AccountTransaction(long amount) {
        this.amount = amount;
    }

    @PrePersist
    public void prePersist() {
        setCreatedAt(new Date());
    }
}
