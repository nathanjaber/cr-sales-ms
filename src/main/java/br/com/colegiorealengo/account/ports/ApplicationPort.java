package br.com.colegiorealengo.account.ports;

import br.com.colegiorealengo.account.domains.Account;
import br.com.colegiorealengo.account.domains.Customer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface ApplicationPort {
  Account create(@Valid @NotNull Customer customer, BigDecimal grossSalary);
}
