package com.sensedia.account.adapters.amqp.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BrokerInput {

  @Input(BindConfig.SUBSCRIBE_CUSTOMER_CREATED)
  SubscribableChannel subscribeCustomerCreated();
}
