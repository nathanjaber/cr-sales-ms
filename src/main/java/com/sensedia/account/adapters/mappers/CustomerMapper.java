package com.sensedia.account.adapters.mappers;

import com.sensedia.account.adapters.dtos.CustomerAmqpInboundDto;
import com.sensedia.account.adapters.dtos.CustomerDto;
import com.sensedia.account.domains.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerAmqpInboundDto customerAmqpInboundDto);

    CustomerDto toCustomerDto(Customer customer);
}
