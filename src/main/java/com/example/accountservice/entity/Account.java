package com.example.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/8/2022
 */
@Entity
@Table(name = "account")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @NotNull
    @Column(name = "account_number", nullable = false)
    private long accountNumber;

    @NotNull
    @Column(name = "balance", nullable = false)
    private long balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TransactionHistory> transactionHistories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "fk_customer")
    private Customer customer;

    public Account(Customer customer) {
        this.customer = customer;
    }

    @PrePersist
    public void prePersist() {
        setCreatedTime(new Date());
    }

    public void addTransaction(TransactionHistory transaction) {
        transactionHistories.add(transaction);
        balance = balance + transaction.getAmount();
        transaction.setAccount(this);
    }

}
