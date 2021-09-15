package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

@Service
public class MethodArgumentNotValidExceptionResolver extends BaseResolver
    implements Resolver<MethodArgumentNotValidException> {

  @Override
  public DefaultErrorResponse getErrorResponse(MethodArgumentNotValidException e) {
    return new DefaultErrorResponse(HttpStatus.BAD_REQUEST, buildItems(e.getBindingResult()));
  }

  private String buildItems(BindingResult bindingResult) {
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();

    if (fieldErrors.isEmpty()) return StringUtils.EMPTY;

    Optional<FieldError> optional = fieldErrors.stream().findFirst();

    if (optional.isEmpty()) return StringUtils.EMPTY;

    return convertToSnakeCase(fieldErrors.get(0).getField())
        + " "
        + optional.get().getDefaultMessage();
  }
}
