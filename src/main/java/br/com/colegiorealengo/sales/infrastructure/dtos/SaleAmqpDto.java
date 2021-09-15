package br.com.colegiorealengo.sales.infrastructure.dtos;

import br.com.colegiorealengo.sales.infrastructure.dtos.serializers.MonetarySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

public class SaleAmqpDto {

    private String id;

    private String customerDocumentNumber;

    private String customerFirstName;

    private String customerLastName;

    private String customerCardNumber;

    private String productId;

    private boolean validated;

    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    @JsonSerialize(using = MonetarySerializer.class)
    private BigDecimal value;

    public SaleAmqpDto(String id,
                       String customerDocumentNumber,
                       String customerFirstName,
                       String customerLastName,
                       String customerCardNumber,
                       String productId,
                       BigDecimal value,
                       boolean validated) {
        this.id = id;
        this.customerDocumentNumber = customerDocumentNumber;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerCardNumber = customerCardNumber;
        this.productId = productId;
        this.value = value;
        this.validated = validated;
    }

    public String getId() {
        return id;
    }

    public String getCustomerDocumentNumber() {
        return customerDocumentNumber;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getCustomerCardNumber() {
        return customerCardNumber;
    }

    public String getProductId() {
        return productId;
    }

    public boolean isValidated() {
        return validated;
    }

    public BigDecimal getValue() {
        return value;
    }
}
