package com.sensedia.account.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@Document(collection = "account")
@NoArgsConstructor
public class Account {

    @Id
    private String id;

    @Indexed(unique = true)
    private String number;

    private String agency;

    private AccountType type;

    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal balance;

    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal availableCreditLimit;

    private Customer customer;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void setRandomAccountType() {
        this.setType(AccountType.randomAccountType());
    }

    public void setRandomAgency() {
        this.setAgency(Agency.randomAgency());
    }

    public void setRandomNumber() {
        Random random = new Random();
        int randomAccountNumber = random.ints(10000000, 99999999).findFirst().getAsInt();
        this.setNumber(String.valueOf(randomAccountNumber));
    }

    public void setAvailableCreditLimitBasedOnGrossSalary(BigDecimal grossSalary) {
        this.availableCreditLimit = grossSalary.multiply(BigDecimal.valueOf(2));
    }


}
