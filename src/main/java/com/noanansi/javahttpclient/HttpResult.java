package com.noanansi.javahttpclient;

import java.time.Instant;
import java.util.Optional;

public class HttpResult<T> {

  private final Optional<T> payload;
  private final Optional<Throwable> error;
  private final Instant executedIn = Instant.now();

  private HttpResult(final T payload) {
    this.payload = Optional.of(payload);
    this.error = Optional.empty();
  }

  private HttpResult(final Throwable error) {
    this.payload = Optional.empty();
    this.error = Optional.of(error);
  }

  public static <T> HttpResult<T> success(final T result) {
    return new HttpResult<>(result);
  }

  public static <T> HttpResult<T> failed(final Throwable error) {
    return new HttpResult<>(error);
  }

  public Boolean isSuccessfully() {
    return error.isEmpty();
  }

  public Optional<T> getPayload() {
    return payload;
  }

  public Optional<Throwable> getError() {
    return error;
  }

  public Instant getExecutedIn() {
    return executedIn;
  }

}
