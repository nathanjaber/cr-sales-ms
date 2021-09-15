package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class IllegalArgumentExceptionResolver implements Resolver<IllegalArgumentException> {

  @Override
  public DefaultErrorResponse getErrorResponse(IllegalArgumentException e) {
    return new DefaultErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
  }
}
