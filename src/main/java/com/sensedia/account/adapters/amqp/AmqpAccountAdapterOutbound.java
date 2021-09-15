package com.sensedia.account.adapters.amqp;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import com.sensedia.account.adapters.amqp.config.BrokerOutput;
import com.sensedia.account.adapters.mappers.AccountMapper;
import com.sensedia.account.domains.Account;
import com.sensedia.account.ports.AmqpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.sensedia.commons.headers.DefaultHeader.APP_ID_HEADER_NAME;
import static com.sensedia.commons.headers.DefaultHeader.EVENT_NAME_HEADER_HEADER;
import static com.sensedia.account.adapters.amqp.config.EventConfig.ACCOUNT_CREATION_EVENT_NAME;
import static com.sensedia.account.adapters.amqp.config.EventConfig.ACCOUNT_OPERATION_ERROR_EVENT_NAME;

@Slf4j
@Service
@EnableBinding({BrokerOutput.class})
public class AmqpAccountAdapterOutbound implements AmqpPort {

  private final BrokerOutput output;
  private final AccountMapper accountMapper;

  @Value("${spring.application.name}")
  protected String appId;

  @Autowired
  public AmqpAccountAdapterOutbound(BrokerOutput output, AccountMapper accountMapper) {
    this.output = output;
    this.accountMapper = accountMapper;
  }

  @Override
  public void publishAccountCreation(Account account) {
    log.info("account {} from customer.document {} to publish", account.getNumber(), account.getCustomer().getDocument());
    sendMessage(output.publishAccountCreated(), account, ACCOUNT_CREATION_EVENT_NAME);
    log.info("account {} from customer.document {} published", account.getNumber(), account.getCustomer().getDocument());
  }

  @Override
  public void publishAccountOperationError(DefaultErrorResponse errorResponse) {
    log.error("error in account creation");
    sendMessage(output.publishAccountOperationError(), errorResponse, ACCOUNT_OPERATION_ERROR_EVENT_NAME);
  }

  private void sendMessage(MessageChannel channel, Account account, String eventName) {
    sendMessage(channel, accountMapper.toAccountAmqpDto(account), eventName);
  }

  private void sendMessage(MessageChannel channel, Object object, String eventName) {
    channel.send(
        MessageBuilder.withPayload(object)
            .setHeader(EVENT_NAME_HEADER_HEADER, eventName)
            .setHeader(APP_ID_HEADER_NAME, appId)
            .build());
  }
}
