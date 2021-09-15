package br.com.colegiorealengo.sales.commons;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.MessageHeaders;

import java.io.IOException;

public class BrokerResponse {
  private String payload;
  private MessageHeaders headers;
  private ObjectMapper mapper;

  public BrokerResponse(String payload, MessageHeaders headers, ObjectMapper mapper) {
    this.payload = payload;
    this.headers = headers;
    this.mapper = mapper;
  }

  public <T> T getPayload(Class<T> clazz) throws IOException {
    return (T) mapper.readValue(payload, clazz);
  }

  public <T> T getPayload(TypeReference<T> valueType) throws IOException {
    return (T) mapper.readValue(payload, valueType);
  }

  public MessageHeaders getHeaders() {
    return headers;
  }
}
