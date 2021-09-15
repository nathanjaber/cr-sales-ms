package br.com.colegiorealengo.sales.ports;

import br.com.colegiorealengo.commons.errors.domains.DefaultErrorResponse;
import br.com.colegiorealengo.sales.domain.Sale;

public interface AmqpPort {

  void publishSaleCreated(Sale sale);

  void publishSaleOperationError(DefaultErrorResponse errorResponse);
}
