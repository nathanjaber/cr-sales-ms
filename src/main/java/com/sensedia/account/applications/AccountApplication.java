package com.sensedia.account.applications;

import com.sensedia.account.domains.Account;
import com.sensedia.account.domains.Customer;
import com.sensedia.account.ports.AmqpPort;
import com.sensedia.account.ports.ApplicationPort;
import com.sensedia.account.ports.RepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Slf4j
@Service
@Transactional
@Validated
public class AccountApplication implements ApplicationPort {

    private final AmqpPort amqpPort;
    private final RepositoryPort repository;

    @Autowired
    public AccountApplication(AmqpPort amqpPort, RepositoryPort repository) {
        this.amqpPort = amqpPort;
        this.repository = repository;
    }

    @Override
    public Account create(@Valid @NotNull Customer customer, BigDecimal grossSalary) {
        log.info("creating new account");
        Account account = new Account();

        account.setRandomAgency();
        account.setRandomAccountType();
        account.setBalance(grossSalary);
        account.setAvailableCreditLimitBasedOnGrossSalary(grossSalary);
        account.setCustomer(customer);

        this.createAccount(account);
        amqpPort.publishAccountCreation(account);

        return account;
    }

    private void createAccount(Account account) {
        try {
            account.setRandomNumber();
            log.info("account {} from customer.document {} to save", account.getNumber(), account.getCustomer().getDocument());
            repository.save(account);
            log.info("account {} from customer.document {} saved", account.getNumber(), account.getCustomer().getDocument());
        } catch (DuplicateKeyException e) {
            log.error("account.number {} already in use", account.getNumber());
            createAccount(account);
        } catch (Exception e) {
            log.error("Error in create account", account.getNumber());
            throw e;
        }
    }
}
