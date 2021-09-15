package com.sensedia.commons.errors.exceptions;

import org.springframework.http.HttpStatus;

public class PreConditionException extends ApplicationException {

  public PreConditionException() {
    this(null, null, null, null);
  }

  public PreConditionException(String detail) {
    this(detail, null, null, null);
  }

  public PreConditionException(String detail, String type) {
    this(detail, type, null, null);
  }

  public PreConditionException(String detail, String type, String title) {
    this(detail, type, title, null);
  }

  public PreConditionException(Throwable cause) {
    this(null, cause);
  }

  public PreConditionException(String detail, Throwable cause) {
    this(detail, null, cause);
  }

  public PreConditionException(String detail, String type, Throwable cause) {
    this(detail, type, null, cause);
  }

  public PreConditionException(String detail, String type, String title, Throwable cause) {
    super(HttpStatus.PRECONDITION_FAILED, detail, type, title, cause);
  }
}
