package br.com.colegiorealengo.sales.domain;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Document(collection = "sales")
public class Sale {

    @Id
    private String id;

    @CPF
    private String customerDocumentNumber;

    private String customerFirstName;

    private String customerLastName;

    private String customerCardNumber;

    private String productId;

    private boolean validated;

    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal value;

    public Sale() {
    }

    public Sale(String id,
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

    public Sale(String customerDocumentNumber,
                String customerFirstName,
                String customerLastName,
                String customerCardNumber,
                String productId,
                BigDecimal value) {
        this.customerDocumentNumber = customerDocumentNumber;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerCardNumber = customerCardNumber;
        this.productId = productId;
        this.validated = false;
        this.value = value;
    }

    public String getId() {
        return id;
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

    public String getCustomerDocumentNumber() {
        return customerDocumentNumber;
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public boolean isValidated() {
        return validated;
    }


}
