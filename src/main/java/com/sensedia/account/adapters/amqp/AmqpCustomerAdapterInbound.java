package com.sensedia.account.adapters.amqp;

import com.sensedia.account.adapters.amqp.config.BindConfig;
import com.sensedia.account.adapters.amqp.config.BrokerInput;
import com.sensedia.account.adapters.dtos.CustomerAmqpInboundDto;
import com.sensedia.account.adapters.mappers.CustomerMapper;
import com.sensedia.account.domains.Customer;
import com.sensedia.account.ports.AmqpPort;
import com.sensedia.account.ports.ApplicationPort;
import com.sensedia.commons.errors.resolvers.ExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@EnableBinding(BrokerInput.class)
public class AmqpCustomerAdapterInbound {

  private final ApplicationPort applicationPort;
  private final CustomerMapper customerMapper;
  private final AmqpPort amqpPort;
  private final ExceptionResolver exceptionResolver;

  public AmqpCustomerAdapterInbound(
      ApplicationPort applicationPort,
      CustomerMapper customerMapper,
      AmqpPort amqpPort,
      ExceptionResolver exceptionResolver) {
    this.applicationPort = applicationPort;
    this.customerMapper = customerMapper;
    this.amqpPort = amqpPort;
    this.exceptionResolver = exceptionResolver;
  }

  @StreamListener(target = BindConfig.SUBSCRIBE_CUSTOMER_CREATED)
  public void subscribeExchangeUserCreated(CustomerAmqpInboundDto customerAmqpInboundDto) {
    log.info("listened customer created");
    try {
      Customer customer = customerMapper.toCustomer(customerAmqpInboundDto);
      applicationPort.create(customer, customerAmqpInboundDto.getGrossSalary());
    } catch (Exception e) {
      amqpPort.publishAccountOperationError(
          exceptionResolver.solve(e).addOriginalMessage(customerAmqpInboundDto));
    }
  }
}
