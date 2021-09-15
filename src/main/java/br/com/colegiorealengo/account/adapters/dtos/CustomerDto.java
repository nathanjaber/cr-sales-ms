package br.com.colegiorealengo.account.adapters.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {

    private String firstName;

    private String lastName;

    private String document;
}
