package br.com.colegiorealengo.sales.infrastructure.amqp.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BrokerInput {

  @Input(BindConfig.SUBSCRIBE_SALE_VALIDATED)
  SubscribableChannel subscribeSaleValidated();
}
