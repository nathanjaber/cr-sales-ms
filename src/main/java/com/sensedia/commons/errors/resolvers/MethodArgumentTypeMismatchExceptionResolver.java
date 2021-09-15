package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Service
public class MethodArgumentTypeMismatchExceptionResolver extends BaseResolver
    implements Resolver<MethodArgumentTypeMismatchException> {

  @Override
  public DefaultErrorResponse getErrorResponse(MethodArgumentTypeMismatchException e) {
    return new DefaultErrorResponse(HttpStatus.BAD_REQUEST, buildErrorMessage(e));
  }

  private String buildErrorMessage(MethodArgumentTypeMismatchException e) {
    return StringUtils.join(convertToSnakeCase(e.getName()), " ", invalidFieldMessage);
  }
}
