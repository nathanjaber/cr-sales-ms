package br.com.colegiorealengo.sales.infrastructure.dtos;

import br.com.colegiorealengo.sales.infrastructure.dtos.serializers.MonetarySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

public class SaleDto {

    private final String id;

    private final String customerDocumentNumber;

    private final String customerFirstName;

    private final String customerLastName;

    private final String customerCardNumber;

    private final String productId;

    private final Boolean validated;

    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    @JsonSerialize(using = MonetarySerializer.class)
    private final BigDecimal value;

    public SaleDto(String id,
                   String customerDocumentNumber,
                   String customerFirstName,
                   String customerLastName,
                   String customerCardNumber,
                   String productId,
                   Boolean validated,
                   BigDecimal value) {
        this.id = id;
        this.customerDocumentNumber = customerDocumentNumber;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerCardNumber = customerCardNumber;
        this.productId = productId;
        this.validated = validated;
        this.value = value;
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
