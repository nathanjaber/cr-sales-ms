package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestHeaderException;

@Service
public class MissingRequestHeaderExceptionResolver extends BaseResolver
    implements Resolver<MissingRequestHeaderException> {

  @Override
  public DefaultErrorResponse getErrorResponse(MissingRequestHeaderException e) {
    return new DefaultErrorResponse(HttpStatus.BAD_REQUEST, buildErrorMessage(e));
  }

  private String buildErrorMessage(MissingRequestHeaderException e) {
    return StringUtils.join(convertToSnakeCase(e.getHeaderName()), " ", missingFieldMessage);
  }
}
