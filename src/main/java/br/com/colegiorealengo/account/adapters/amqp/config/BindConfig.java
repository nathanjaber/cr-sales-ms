package br.com.colegiorealengo.account.adapters.amqp.config;

public class BindConfig {

  public static final String SUBSCRIBE_CUSTOMER_CREATED = "subscribeCustomerCreated";
  public static final String PUBLISH_ACCOUNT_CREATED = "publishAccountCreated";
  public static final String PUBLISH_ACCOUNT_OPERATION_ERROR = "publishAccountOperationError";

  private BindConfig() {}
}
