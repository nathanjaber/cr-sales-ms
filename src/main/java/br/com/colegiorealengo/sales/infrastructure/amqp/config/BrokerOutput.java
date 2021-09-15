package br.com.colegiorealengo.sales.infrastructure.amqp.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface BrokerOutput {

  @Output(BindConfig.PUBLISH_SALE_CREATED)
  MessageChannel publishSaleCreated();

  @Output(BindConfig.PUBLISH_SALE_OPERATION_ERROR)
  MessageChannel publishSaleOperationError();
}
