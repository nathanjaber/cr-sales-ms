package br.com.colegiorealengo.sales.infrastructure.dtos;

import br.com.colegiorealengo.sales.infrastructure.dtos.serializers.MonetarySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

public class SaleCreationDto {

    @CPF
    private String customerDocumentNumber;

    private String customerFirstName;

    private String customerLastName;

    private String customerCardNumber;

    private String productId;

    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    @JsonSerialize(using = MonetarySerializer.class)
    private BigDecimal value;



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

    public BigDecimal getValue() {
        return value;
    }
}
