package br.com.colegiorealengo.sales.infrastructure.amqp.config;

public class BindConfig {

  public static final String SUBSCRIBE_SALE_VALIDATED = "subscribeSaleValidated";
  public static final String PUBLISH_SALE_CREATED = "publishSaleCreated";
  public static final String PUBLISH_SALE_OPERATION_ERROR = "publishSaleOperationError";

  private BindConfig() {}
}
