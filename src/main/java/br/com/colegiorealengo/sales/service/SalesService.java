package br.com.colegiorealengo.sales.service;

import br.com.colegiorealengo.commons.errors.exceptions.NotFoundException;
import br.com.colegiorealengo.sales.domain.Sale;
import br.com.colegiorealengo.sales.ports.AmqpPort;
import br.com.colegiorealengo.sales.ports.RepositoryPort;
import br.com.colegiorealengo.sales.ports.SalesPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@Validated
public class SalesService implements SalesPort {

    private final AmqpPort amqpPort;
    private final RepositoryPort repository;

    @Autowired
    public SalesService(AmqpPort amqpPort, RepositoryPort repository) {
        this.amqpPort = amqpPort;
        this.repository = repository;
    }

    @Override
    public Sale create(@Valid Sale sale) {
        log.info("Creating new sale");

        log.info("Sale from customer {} product {} and value {} to save",
                sale.getCustomerDocumentNumber(),
                sale.getProductId(),
                sale.getValue());

        Sale savedSale = repository.save(sale);

        log.info("Sale from customer {} product {} and value {} saved: {}",
                savedSale.getCustomerDocumentNumber(),
                savedSale.getProductId(),
                savedSale.getValue(),
                savedSale.getId());

        amqpPort.publishSaleCreated(savedSale);

        return savedSale;
    }

    @Override
    public void update(@Valid Sale sale) {
        log.info("Updating sale");

        log.info("Sale {} from customer {} product {} and value {} to update",
                sale.getId(),
                sale.getCustomerDocumentNumber(),
                sale.getProductId(),
                sale.getValue());


        this.findById(sale.getId());
        repository.save(sale);

        log.info("Sale {} from customer {} product {} and value {} updated",
                sale.getId(),
                sale.getCustomerDocumentNumber(),
                sale.getProductId(),
                sale.getValue());
    }

    @Override
    public Sale findById(String id) {
        Optional<Sale> optionalSale = repository.findById(id);

        if (optionalSale.isEmpty()) {
            log.error("sales {} not found", id);
            throw new NotFoundException("sale not found");
        } else {
            log.info("sales {} founded", id);
            return optionalSale.get();
        }
    }
}
