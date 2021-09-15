package com.sensedia.account.ports;

import com.sensedia.account.domains.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPort extends CrudRepository<Account, String> {}
