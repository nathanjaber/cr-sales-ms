package com.sensedia.commons.errors.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DefaultErrorResponse<T> {

  public static final String GATEWAY_TIMEOUT =
      "O servidor encontrou um erro temporário e não completou a requisição.";
  public static final String SERVICE_UNAVAILABLE_MESSAGE = "O serviço está indisponível.";
  public static final String BAD_GATEWAY_MESSAGE =
      "O servidor encontrou um erro temporário e não completou a requisição.";
  public static final String INTERNAL_SERVER_ERROR_MESSAGE =
      "Ocorreu um erro inesperado na aplicação.";
  public static final String TOO_MANY_REQUESTS_MESSAGE = "Houve uma sobrecarga de requisições.";
  public static final String UNPROCESSABLE_ENTITY_MESSAGE = "Erro na validação de informações.";
  public static final String UNSUPPORTED_MEDIA_TYPE_MESSAGE = "Erro no formato da mensagem.";
  public static final String PAYLOAD_TOO_LARGE_MESSAGE =
      "A requisição excedeu o limite de tamanho.";
  public static final String PRECONDITION_FAILED_MESSAGE =
      "Uma ou mais condições da requisição foi negada pelo servidor.";
  public static final String NOT_FOUND_MESSAGE = "Recurso não encontrado.";
  public static final String FORBIDDEN_MESSAGE = "Requisição não é permitida.";
  public static final String UNAUTHORIZED_MESSAGE =
      "Credenciais inválidas. Requisição não autorizada.";
  public static final String BAD_REQUEST_MESSAGE = "Ocorreu um erro na validação da requisição.";
  public static final String PAYMENT_REQUIRED_MESSAGE = "Erro ao efetuar o pagamento.";

  private static final Map<HttpStatus, String> DEFAULT_DETAIL = new HashMap<>();

  static {
    DEFAULT_DETAIL.put(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.FORBIDDEN, FORBIDDEN_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.PRECONDITION_FAILED, PRECONDITION_FAILED_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.PAYLOAD_TOO_LARGE, PAYLOAD_TOO_LARGE_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.UNSUPPORTED_MEDIA_TYPE, UNSUPPORTED_MEDIA_TYPE_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.UNPROCESSABLE_ENTITY, UNPROCESSABLE_ENTITY_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.TOO_MANY_REQUESTS, TOO_MANY_REQUESTS_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.BAD_GATEWAY, BAD_GATEWAY_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE_MESSAGE);
    DEFAULT_DETAIL.put(HttpStatus.GATEWAY_TIMEOUT, GATEWAY_TIMEOUT);
    DEFAULT_DETAIL.put(HttpStatus.PAYMENT_REQUIRED, PAYMENT_REQUIRED_MESSAGE);
  }

  private String type;
  private String title;
  private String detail;
  private HttpStatus status;
  private T originalMessage;

  public DefaultErrorResponse(int statusCode) {
    this(HttpStatus.valueOf(statusCode), null);
  }

  public DefaultErrorResponse(int statusCode, String detail) {
    this(HttpStatus.valueOf(statusCode), detail);
  }

  public DefaultErrorResponse(int statusCode, String detail, String type) {
    this(HttpStatus.valueOf(statusCode), detail, type);
  }

  public DefaultErrorResponse(
      @JsonProperty("status") int statusCode,
      @JsonProperty("detail") String detail,
      @JsonProperty("type") String type,
      @JsonProperty("title") String title) {
    this(HttpStatus.valueOf(statusCode), detail, type, title);
  }

  public DefaultErrorResponse(HttpStatus status) {
    this(status, null);
  }

  public DefaultErrorResponse(HttpStatus status, String detail) {
    this(status, detail, null, null);
  }

  public DefaultErrorResponse(HttpStatus status, String detail, String type) {
    this(status, detail, type, null);
  }

  public DefaultErrorResponse(HttpStatus status, String detail, String type, String title) {
    if (status == null) throw new IllegalArgumentException("http status cannot be null");

    this.status = status;
    this.detail = detail;
    this.type = type;
    this.title = title;

    if (StringUtils.isBlank(title)) {
      this.title = status.getReasonPhrase();
    }

    if (StringUtils.isBlank(detail)) {
      this.detail = DEFAULT_DETAIL.get(status);
    }
  }

  public String getType() {
    return type;
  }

  public String getTitle() {
    return title;
  }

  public String getDetail() {
    return detail;
  }

  public int getStatus() {
    return status.value();
  }

  @SuppressWarnings("unchecked")
  public DefaultErrorResponse addOriginalMessage(Object originalMessage) {
    this.originalMessage = (T) originalMessage;
    return this;
  }


  public T getOriginalMessage() {
    return originalMessage;
  }

  @JsonIgnore
  public HttpStatus getHttpStatus() {
    return status;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("type", type)
        .append("title", title)
        .append("detail", detail)
        .append("status", status)
        .append("originalMessage", originalMessage)
        .toString();
  }
}
