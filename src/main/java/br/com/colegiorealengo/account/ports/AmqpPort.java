package br.com.colegiorealengo.account.ports;

import br.com.colegiorealengo.commons.errors.domains.DefaultErrorResponse;
import br.com.colegiorealengo.account.domains.Account;

public interface AmqpPort {

  void publishAccountCreation(Account account);

  void publishAccountOperationError(DefaultErrorResponse errorResponse);
}
