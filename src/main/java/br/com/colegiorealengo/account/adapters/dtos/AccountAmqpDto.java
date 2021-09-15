package br.com.colegiorealengo.account.adapters.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AccountAmqpDto {

    private String id;

    private String number;

    private String agency;

    private String type;

    private BigDecimal balance;

    private BigDecimal availableCreditLimit;

    private CustomerDto customer;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
