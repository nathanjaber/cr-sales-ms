package com.sensedia.commons.beans;

import javax.validation.*;
import java.util.Set;

public class BeanValidator {

  private BeanValidator() {}

  public static void validate(Object obj, Class<?>... groups) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Object>> errors = validator.validate(obj, groups);
    if (!errors.isEmpty()) {
      throw new ConstraintViolationException(errors.toString(), errors);
    }
  }
}
