package br.com.colegiorealengo.sales.ports;

import br.com.colegiorealengo.sales.domain.Sale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface SalesPort {
  Sale create(@Valid Sale sale);

  void update(@Valid Sale sale);

  Sale findById(String id);
}
