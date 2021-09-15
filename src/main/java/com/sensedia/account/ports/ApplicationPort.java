package com.sensedia.account.ports;

import com.sensedia.account.domains.Account;
import com.sensedia.account.domains.Customer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface ApplicationPort {
  Account create(@Valid @NotNull Customer customer, BigDecimal grossSalary);
}
