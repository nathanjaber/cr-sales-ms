package br.com.colegiorealengo.account.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    private String firstName;

    private String lastName;

    private String document;

}
