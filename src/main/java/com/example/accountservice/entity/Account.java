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

    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @NotNull
    @Column(name = "balance", nullable = false)
    private long balance;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountTransaction> accountTransactions = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "fk_customer")
    private Customer customer;

    public Account(Customer customer) {
        this.customer = customer;
    }

    @PrePersist
    public void prePersist() {
        setCreatedAt(new Date());
    }

    public void addTransaction(AccountTransaction transaction) {
        accountTransactions.add(transaction);
        balance = balance + transaction.getAmount();
        transaction.setNewBalance(balance);
        transaction.setAccount(this);
    }

    public void removeTransaction(AccountTransaction transaction) {
        accountTransactions.remove(transaction);
        transaction.setAccount(null);
    }
}
