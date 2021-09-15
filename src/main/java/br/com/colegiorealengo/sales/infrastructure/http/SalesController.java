package br.com.colegiorealengo.sales.infrastructure.http;

import br.com.colegiorealengo.sales.domain.Sale;
import br.com.colegiorealengo.sales.infrastructure.dtos.SaleCreationDto;
import br.com.colegiorealengo.sales.infrastructure.dtos.SaleDto;
import br.com.colegiorealengo.sales.ports.SalesPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesPort salesPort;

    @Autowired
    public SalesController(
            SalesPort salesPort
    ) {
        this.salesPort = salesPort;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        Sale sale = salesPort.findById(id);
        SaleDto saleDto = new SaleDto(
                sale.getId(),
                sale.getCustomerDocumentNumber(),
                sale.getCustomerFirstName(),
                sale.getCustomerLastName(),
                sale.getCustomerCardNumber(),
                sale.getProductId(),
                sale.isValidated(),
                sale.getValue()
        );

        return ResponseEntity.ok(saleDto);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SaleCreationDto saleCreationDto) {
        Sale sale = new Sale(
                saleCreationDto.getCustomerDocumentNumber(),
                saleCreationDto.getCustomerFirstName(),
                saleCreationDto.getCustomerLastName(),
                saleCreationDto.getCustomerCardNumber(),
                saleCreationDto.getProductId(),
                saleCreationDto.getValue()
        );

        String idCreated = this.salesPort.create(sale).getId();
        return ResponseEntity.created(buildLocation(idCreated)).build();
    }

    public URI buildLocation(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/partners/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
