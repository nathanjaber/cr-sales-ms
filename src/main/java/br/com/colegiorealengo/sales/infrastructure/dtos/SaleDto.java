package br.com.colegiorealengo.sales.infrastructure.dtos;

import br.com.colegiorealengo.sales.infrastructure.dtos.serializers.MonetarySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

public class SaleDto {

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

    public SaleDto(String id,
                   String customerDocumentNumber,
                   String customerFirstName,
                   String customerLastName,
                   String customerCardNumber,
                   String productId,
                   boolean validated,
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
}
