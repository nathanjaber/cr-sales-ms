package br.com.colegiorealengo.sales.ports;

import br.com.colegiorealengo.sales.domain.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPort extends MongoRepository<Sale, String> {}
