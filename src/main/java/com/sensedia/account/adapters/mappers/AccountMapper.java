package com.sensedia.account.adapters.mappers;

import com.sensedia.account.adapters.dtos.AccountAmqpDto;
import com.sensedia.account.domains.Account;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {CustomerMapper.class})
public interface AccountMapper {

    @Mapping(target = "type", expression = "java( account.getType().name() )")
    AccountAmqpDto toAccountAmqpDto(Account account);
}
