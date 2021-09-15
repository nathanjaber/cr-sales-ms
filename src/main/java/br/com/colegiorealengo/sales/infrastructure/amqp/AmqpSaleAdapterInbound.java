package br.com.colegiorealengo.sales.infrastructure.amqp;

import br.com.colegiorealengo.commons.errors.resolvers.ExceptionResolver;
import br.com.colegiorealengo.sales.domain.Sale;
import br.com.colegiorealengo.sales.infrastructure.amqp.config.BindConfig;
import br.com.colegiorealengo.sales.infrastructure.amqp.config.BrokerInput;
import br.com.colegiorealengo.sales.infrastructure.dtos.SaleAmqpDto;
import br.com.colegiorealengo.sales.ports.AmqpPort;
import br.com.colegiorealengo.sales.ports.SalesPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@EnableBinding(BrokerInput.class)
public class AmqpSaleAdapterInbound {

  private final SalesPort salesPort;
  private final AmqpPort amqpPort;
  private final ExceptionResolver exceptionResolver;

  public AmqpSaleAdapterInbound(
      SalesPort salesPort,
      AmqpPort amqpPort,
      ExceptionResolver exceptionResolver) {
    this.salesPort = salesPort;
    this.amqpPort = amqpPort;
    this.exceptionResolver = exceptionResolver;
  }

  @StreamListener(target = BindConfig.SUBSCRIBE_SALE_VALIDATED)
  public void subscribeSaleValidated(SaleAmqpDto saleAmqp) {
    log.info("listened customer created");
    try {
      Sale sale = new Sale(
              saleAmqp.getId(),
              saleAmqp.getCustomerDocumentNumber(),
              saleAmqp.getCustomerFirstName(),
              saleAmqp.getCustomerLastName(),
              saleAmqp.getCustomerCardNumber(),
              saleAmqp.getProductId(),
              saleAmqp.isValidated(),
              saleAmqp.getValue()
      );
      salesPort.update(sale);
    } catch (Exception e) {
      amqpPort.publishSaleOperationError(
          exceptionResolver.solve(e).addOriginalMessage(saleAmqp));
    }
  }
}
