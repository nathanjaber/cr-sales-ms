package com.sensedia.commons.errors.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {

  public NotFoundException() {
    this(null, null, null, null);
  }

  public NotFoundException(String detail) {
    this(detail, null, null, null);
  }

  public NotFoundException(String detail, String type) {
    this(detail, type, null, null);
  }

  public NotFoundException(String detail, String type, String title) {
    this(detail, type, title, null);
  }

  public NotFoundException(Throwable cause) {
    this(null, cause);
  }

  public NotFoundException(String detail, Throwable cause) {
    this(detail, null, cause);
  }

  public NotFoundException(String detail, String type, Throwable cause) {
    this(detail, type, null, cause);
  }

  public NotFoundException(String detail, String type, String title, Throwable cause) {
    super(HttpStatus.NOT_FOUND, detail, type, title, cause);
  }
}
