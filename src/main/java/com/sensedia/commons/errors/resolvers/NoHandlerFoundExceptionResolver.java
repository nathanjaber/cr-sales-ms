package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

@Service
public class NoHandlerFoundExceptionResolver implements Resolver<NoHandlerFoundException> {

  @Override
  public DefaultErrorResponse getErrorResponse(NoHandlerFoundException e) {
    return new DefaultErrorResponse(HttpStatus.NOT_FOUND);
  }
}
