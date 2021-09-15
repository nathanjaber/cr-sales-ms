package com.sensedia.commons.errors.resolvers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

@Service
public class HttpMessageNotReadableExceptionResolver extends BaseResolver
    implements Resolver<HttpMessageNotReadableException> {

  @Override
  public DefaultErrorResponse getErrorResponse(HttpMessageNotReadableException e) {
    return new DefaultErrorResponse(HttpStatus.BAD_REQUEST, handleNotReadableMessage(e));
  }

  private String buildErrorMessage(JsonMappingException e) {
    String fieldName = e.getPath().get(0).getFieldName();
    return StringUtils.join(convertToSnakeCase(fieldName), " ", invalidFieldMessage);
  }

  private String handleNotReadableMessage(HttpMessageNotReadableException e) {
    if (e.getCause() instanceof JsonMappingException) {
      return buildErrorMessage((JsonMappingException) e.getCause());
    }

    return e.getMessage();
  }
}
