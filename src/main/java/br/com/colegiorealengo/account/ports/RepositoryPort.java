package br.com.colegiorealengo.account.ports;

import br.com.colegiorealengo.account.domains.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPort extends CrudRepository<Account, String> {}
