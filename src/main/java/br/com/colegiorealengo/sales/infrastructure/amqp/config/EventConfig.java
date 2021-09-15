package br.com.colegiorealengo.sales.infrastructure.amqp.config;

public final class EventConfig {

  public static final String SALE_CREATED_EVENT_NAME = "SaleCreated";
  public static final String SALE_OPERATION_ERROR_EVENT_NAME = "SaleOperationError";

  private EventConfig() {}
}
