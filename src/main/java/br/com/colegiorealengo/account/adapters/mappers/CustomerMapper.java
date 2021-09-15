package br.com.colegiorealengo.account.adapters.mappers;

import br.com.colegiorealengo.account.adapters.dtos.CustomerAmqpInboundDto;
import br.com.colegiorealengo.account.adapters.dtos.CustomerDto;
import br.com.colegiorealengo.account.domains.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerAmqpInboundDto customerAmqpInboundDto);

    CustomerDto toCustomerDto(Customer customer);
}
