package com.sensedia.account.adapters.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerAmqpInboundDto {
    private String id;

    private String firstName;

    private String lastName;

    private String document;

    private BigDecimal grossSalary;

    private Instant createdAt;

    private Instant updatedAt;
}
