package br.com.colegiorealengo.commons.errors.resolvers;

import br.com.colegiorealengo.commons.errors.domains.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Service
public class HttpRequestMethodNotSupportedExceptionResolver
    implements Resolver<HttpRequestMethodNotSupportedException> {

  @Override
  public DefaultErrorResponse getErrorResponse(HttpRequestMethodNotSupportedException e) {
    return new DefaultErrorResponse(HttpStatus.METHOD_NOT_ALLOWED);
  }
}
