package com.sensedia.commons.errors.resolvers;

import com.sensedia.commons.errors.domains.DefaultErrorResponse;

public interface Resolver<T extends Throwable> {
  DefaultErrorResponse getErrorResponse(T e);
}
