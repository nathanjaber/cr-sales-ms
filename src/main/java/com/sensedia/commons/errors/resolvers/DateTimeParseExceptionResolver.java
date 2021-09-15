package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;

@Service
public class DateTimeParseExceptionResolver extends BaseResolver
    implements Resolver<DateTimeParseException> {

  @Override
  public DefaultErrorResponse getErrorResponse(DateTimeParseException e) {
    return new DefaultErrorResponse(HttpStatus.BAD_REQUEST, buildErrorMessage(e));
  }

  protected String buildErrorMessage(DateTimeParseException e) {
    return StringUtils.join(convertToSnakeCase(e.getMessage()), " ", invalidFieldMessage);
  }
}
