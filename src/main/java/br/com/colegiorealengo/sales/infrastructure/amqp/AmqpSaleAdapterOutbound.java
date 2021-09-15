package br.com.colegiorealengo.sales.infrastructure.amqp;

import br.com.colegiorealengo.commons.errors.domains.DefaultErrorResponse;
import br.com.colegiorealengo.commons.headers.DefaultHeader;
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

    sendMessage(output.publishSaleCreated(), saleAmqpDto, EventConfig.SALE_CREATED_EVENT_NAME);

    log.info("Sale {} published",
            sale.getId());
  }

  @Override
  public void publishSaleOperationError(DefaultErrorResponse errorResponse) {
    log.error("error in sales creation");
    sendMessage(output.publishSaleOperationError(), errorResponse, EventConfig.SALE_OPERATION_ERROR_EVENT_NAME);
  }

  private void sendMessage(MessageChannel channel, Object object, String eventName) {
    channel.send(MessageBuilder.withPayload(object)
            .setHeader(DefaultHeader.EVENT_NAME_HEADER_HEADER, eventName)
            .setHeader(DefaultHeader.APP_ID_HEADER_NAME, appId)
            .build());
  }
}
