package com.sensedia.account.ports;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import com.sensedia.account.domains.Account;

public interface AmqpPort {

  void publishAccountCreation(Account account);

  void publishAccountOperationError(DefaultErrorResponse errorResponse);
}
