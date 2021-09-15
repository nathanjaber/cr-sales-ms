package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

@Service
public class MissingServletRequestParameterExceptionResolver extends BaseResolver
    implements Resolver<MissingServletRequestParameterException> {

  @Override
  public DefaultErrorResponse getErrorResponse(MissingServletRequestParameterException e) {
    return new DefaultErrorResponse(HttpStatus.BAD_REQUEST, buildErrorMessage(e));
  }

  private String buildErrorMessage(MissingServletRequestParameterException e) {
    return StringUtils.join(convertToSnakeCase(e.getParameterName()), " ", missingFieldMessage);
  }
}
