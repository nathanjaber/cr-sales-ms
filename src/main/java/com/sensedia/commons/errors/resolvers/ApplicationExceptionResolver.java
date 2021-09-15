package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import com.sensedia.commons.errors.exceptions.ApplicationException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationExceptionResolver implements Resolver<ApplicationException> {

  @Override
  public DefaultErrorResponse getErrorResponse(ApplicationException e) {
    return e.getDefaultErrorResponse();
  }
}
