package com.sensedia.commons.converters;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

@Service
public class InstantConverter {

  public Instant toInstant(String date) {
    try {
      if (date == null) return null;
      return Instant.parse(date);
    } catch (DateTimeParseException e) {
      return tryLocalDateTime(date);
    }
  }

  private Instant tryLocalDateTime(String date) {
    try {
      return LocalDateTime.parse(date).atZone(ZoneId.systemDefault()).toInstant();
    } catch (DateTimeParseException e) {
      return tryLocalDate(date);
    }
  }

  private Instant tryLocalDate(String date) {
    try {
      return LocalDate.parse(date).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException(
          "Invalid date format for the value [" + date + "]. Use the date in ISO 8601 format", e);
    }
  }
}
