package br.com.colegiorealengo.sales.infrastructure.amqp;

import br.com.colegiorealengo.commons.errors.domains.DefaultErrorResponse;
import br.com.colegiorealengo.sales.domain.Sale;
import br.com.colegiorealengo.sales.infrastructure.amqp.config.BrokerOutput;
import br.com.colegiorealengo.sales.infrastructure.amqp.config.EventConfig;
import br.com.colegiorealengo.sales.infrastructure.dtos.SaleAmqpDto;
import br.com.colegiorealengo.sales.ports.AmqpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static br.com.colegiorealengo.commons.headers.DefaultHeader.APP_ID_HEADER_NAME;
import static br.com.colegiorealengo.commons.headers.DefaultHeader.EVENT_NAME_HEADER_HEADER;

@Slf4j
@Service
@EnableBinding({BrokerOutput.class})
public class AmqpSaleAdapterOutbound implements AmqpPort {

  private final BrokerOutput output;

  @Value("${spring.application.name}")
  protected String appId;

  @Autowired
  public AmqpSaleAdapterOutbound(BrokerOutput output) {
    this.output = output;
  }

  @Override
  public void publishSaleCreated(Sale sale) {
    log.info("Sale {} to publish",
            sale.getId());

    SaleAmqpDto saleAmqpDto = new SaleAmqpDto(
            sale.getId(),
            sale.getCustomerDocumentNumber(),
            sale.getCustomerFirstName(),
            sale.getCustomerLastName(),
            sale.getCustomerCardNumber(),
            sale.getProductId(),
            sale.getValue(),
            sale.isValidated()
    );

    sendMessage(output.publishSaleCreated(), saleAmqpDto, EventConfig.ACCOUNT_CREATION_EVENT_NAME);

    log.info("Sale {} published",
            sale.getId());
  }

  @Override
  public void publishSaleOperationError(DefaultErrorResponse errorResponse) {
    log.error("error in sales creation");
    sendMessage(output.publishSaleOperationError(), errorResponse, EventConfig.ACCOUNT_OPERATION_ERROR_EVENT_NAME);
  }

  private void sendMessage(MessageChannel channel, SaleAmqpDto saleAmqpDto, String eventName) {
    sendMessage(channel, saleAmqpDto, eventName);
  }

  private void sendMessage(MessageChannel channel, Object object, String eventName) {
    channel.send(
        MessageBuilder.withPayload(object)
            .setHeader(EVENT_NAME_HEADER_HEADER, eventName)
            .setHeader(APP_ID_HEADER_NAME, appId)
            .build());
  }
}
