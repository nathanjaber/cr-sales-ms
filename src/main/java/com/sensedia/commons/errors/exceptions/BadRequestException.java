package com.sensedia.commons.errors.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApplicationException {

  public BadRequestException() {
    this(null, null, null, null);
  }

  public BadRequestException(String detail) {
    this(detail, null, null, null);
  }

  public BadRequestException(String detail, String type) {
    this(detail, type, null, null);
  }

  public BadRequestException(String detail, String type, String title) {
    this(detail, type, title, null);
  }

  public BadRequestException(Throwable cause) {
    this(null, cause);
  }

  public BadRequestException(String detail, Throwable cause) {
    this(detail, null, cause);
  }

  public BadRequestException(String detail, String type, Throwable cause) {
    this(detail, type, null, cause);
  }

  public BadRequestException(String detail, String type, String title, Throwable cause) {
    super(HttpStatus.BAD_REQUEST, detail, type, title, cause);
  }
}
