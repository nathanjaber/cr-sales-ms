package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExceptionResolver {

  private final List<Resolver<?>> resolvers = new ArrayList<>();

  public ExceptionResolver(
      ApplicationExceptionResolver applicationExceptionResolver,
      ConstraintViolationExceptionResolver constraintViolationExceptionResolver,
      DateTimeParseExceptionResolver dateTimeParseExceptionResolver,
      HttpMessageNotReadableExceptionResolver httpMessageNotReadableExceptionResolver,
      HttpRequestMethodNotSupportedExceptionResolver httpRequestMethodNotSupportedExceptionResolver,
      IllegalArgumentExceptionResolver illegalArgumentExceptionResolver,
      JsonMappingExceptionResolver jsonMappingExceptionResolver,
      MethodArgumentNotValidExceptionResolver methodArgumentNotValidExceptionResolver,
      MethodArgumentTypeMismatchExceptionResolver methodArgumentTypeMismatchExceptionResolver,
      MissingRequestHeaderExceptionResolver missingRequestHeaderExceptionResolver,
      MissingServletRequestParameterExceptionResolver
          missingServletRequestParameterExceptionResolver,
      NoHandlerFoundExceptionResolver noHandlerFoundExceptionResolver) {

    register(applicationExceptionResolver);
    register(constraintViolationExceptionResolver);
    register(dateTimeParseExceptionResolver);
    register(httpMessageNotReadableExceptionResolver);
    register(httpRequestMethodNotSupportedExceptionResolver);
    register(illegalArgumentExceptionResolver);
    register(jsonMappingExceptionResolver);
    register(methodArgumentNotValidExceptionResolver);
    register(methodArgumentTypeMismatchExceptionResolver);
    register(missingRequestHeaderExceptionResolver);
    register(missingServletRequestParameterExceptionResolver);
    register(noHandlerFoundExceptionResolver);
  }

  public void register(Resolver<?> resolver) {
    resolvers.add(resolver);
  }

  @SuppressWarnings("unchecked")
  public DefaultErrorResponse solve(Throwable e) {

    for (Resolver resolver : resolvers) {
      if (isInstanceOf(resolver, e)) {
        return resolver.getErrorResponse(e);
      }
    }

    return new DefaultErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
  }

  private boolean isInstanceOf(Resolver resolver, Throwable e) {
    try {
      return Class.forName(getClassName(resolver)).isInstance(e);
    } catch (ClassNotFoundException ex) {
      return false;
    }
  }

  private String getClassName(Resolver resolver) {
    return ((ParameterizedType) resolver.getClass().getGenericInterfaces()[0])
        .getActualTypeArguments()[0].getTypeName();
  }
}
