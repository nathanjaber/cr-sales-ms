package br.com.colegiorealengo.commons.errors.resolvers;

import br.com.colegiorealengo.commons.errors.exceptions.ApplicationException;
import br.com.colegiorealengo.commons.errors.domains.DefaultErrorResponse;
import org.springframework.stereotype.Service;

@Service
public class ApplicationExceptionResolver implements Resolver<ApplicationException> {

  @Override
  public DefaultErrorResponse getErrorResponse(ApplicationException e) {
    return e.getDefaultErrorResponse();
  }
}
