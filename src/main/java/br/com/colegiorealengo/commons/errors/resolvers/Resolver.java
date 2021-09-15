package br.com.colegiorealengo.commons.errors.resolvers;

import br.com.colegiorealengo.commons.errors.domains.DefaultErrorResponse;

public interface Resolver<T extends Throwable> {
  DefaultErrorResponse getErrorResponse(T e);
}
