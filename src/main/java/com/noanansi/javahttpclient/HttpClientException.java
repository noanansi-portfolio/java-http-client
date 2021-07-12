package com.noanansi.javahttpclient;

import java.net.http.HttpRequest;

public class HttpClientException extends RuntimeException {

  public HttpClientException(HttpRequest request) {
    super(request.toString());
  }

}
