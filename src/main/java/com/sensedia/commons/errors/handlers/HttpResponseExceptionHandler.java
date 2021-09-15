package com.sensedia.commons.errors.handlers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import com.sensedia.commons.errors.resolvers.ExceptionResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class HttpResponseExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(HttpResponseExceptionHandler.class);

  private final ExceptionResolver exceptionResolver;

  public HttpResponseExceptionHandler(ExceptionResolver exceptionResolver) {
    this.exceptionResolver = exceptionResolver;
  }

  @ExceptionHandler({Throwable.class})
  @ResponseBody
  public DefaultErrorResponse handleApplicationException(HttpServletResponse res, Exception e) {
    log.error("", e);
    DefaultErrorResponse errorResponse = exceptionResolver.solve(e);
    res.setStatus(errorResponse.getStatus());
    return errorResponse;
  }
}
