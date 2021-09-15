package com.sensedia.commons.errors.resolvers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class JsonMappingExceptionResolver extends BaseResolver
    implements Resolver<JsonMappingException> {

  @Override
  public DefaultErrorResponse getErrorResponse(JsonMappingException e) {
    return new DefaultErrorResponse(HttpStatus.BAD_REQUEST, buildErrorMessage(e));
  }

  private String buildErrorMessage(JsonMappingException e) {
    String fieldName = e.getPath().get(0).getFieldName();
    return StringUtils.join(convertToSnakeCase(fieldName), " ", invalidFieldMessage);
  }
}
