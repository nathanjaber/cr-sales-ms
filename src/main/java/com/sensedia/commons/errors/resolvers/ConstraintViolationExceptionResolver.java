package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.Set;

@Service
public class ConstraintViolationExceptionResolver extends BaseResolver
    implements Resolver<ConstraintViolationException> {

  @Override
  public DefaultErrorResponse getErrorResponse(ConstraintViolationException e) {
    return new DefaultErrorResponse(
        HttpStatus.BAD_REQUEST, errorFields(e.getConstraintViolations()));
  }

  private String errorFields(Set<ConstraintViolation<?>> constraintsViolation) {
    if (constraintsViolation == null || constraintsViolation.isEmpty()) return StringUtils.EMPTY;

    Optional<ConstraintViolation<?>> optional = constraintsViolation.stream().findFirst();

    if (optional.isEmpty()) return StringUtils.EMPTY;

    ConstraintViolation<?> constraintViolation = optional.get();
    NodeImpl node = ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode();
    String message = constraintViolation.getMessage();
    return convertToSnakeCase(node.getName()) + " " + message;
  }
}
