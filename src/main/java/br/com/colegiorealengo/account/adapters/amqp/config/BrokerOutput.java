package br.com.colegiorealengo.account.adapters.amqp.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface BrokerOutput {

  @Output(BindConfig.PUBLISH_ACCOUNT_CREATED)
  MessageChannel publishAccountCreated();

  @Output(BindConfig.PUBLISH_ACCOUNT_OPERATION_ERROR)
  MessageChannel publishAccountOperationError();
}
