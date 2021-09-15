package com.sensedia.commons.errors.resolvers;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {"classpath:ValidationMessages.properties"})
public abstract class BaseResolver {

  @Value("${com.sensedia.InvalidField.message}")
  protected String invalidFieldMessage;

  @Value("${com.sensedia.MissingField.message}")
  protected String missingFieldMessage;

  protected String convertToSnakeCase(String value) {
    return CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert(value);
  }
}
