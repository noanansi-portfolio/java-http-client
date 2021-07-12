package com.noanansi.javahttpclient;

import static java.time.temporal.ChronoUnit.SECONDS;

import com.google.gson.Gson;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class consists exclusively of static methods that makes HTTP/2 requests. Its main
 * goal is to hide the implementation details from the rest of the application.
 */
public class HttpRequestHandler {

  private static final Gson gson = new Gson();
  private static final HttpClient client = HttpClient.newHttpClient();

  private static final RetryConfig retryConfig;
  private static final Retry retry;

  static {
    retryConfig = RetryConfig.custom()
        .maxAttempts(3)
        .waitDuration(Duration.of(2, SECONDS))
        .build();
    retry = Retry.of("requestPost", retryConfig);
  }

  private HttpRequestHandler() {
  }

  /**
   * Make a post request.
   *
   * @param url          The destination URL as String. It must include path and request params
   * @param headers      Key value pairs, in this version does not support multiple values for the
   *                     same header
   * @param body         The request body
   * @param responseType Expected response type
   * @param <T>          Class of the expected response type
   * @return Response body
   */
  public static <T> T requestPost(final String url, final Map<String, String> headers,
                                  final String body, final Class<T> responseType) {
    final var request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .headers(extractHeaders(headers))
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .timeout(Duration.ofSeconds(3))
        .build();
    final var response = retry.executeSupplier(() -> call(request));
    return gson.fromJson(response.body(), responseType);
  }

  private static String[] extractHeaders(final Map<String, String> headers) {
    // Each entry is a pair so the output will be a list with the double size of the map
    final var headerList = new ArrayList<String>(headers.size() * 2);
    headers.entrySet().forEach(header -> {
      headerList.add(header.getKey());
      headerList.add(header.getValue());
    });
    return headerList.toArray(new String[] {});
  }

  private static HttpResponse<String> call(HttpRequest request) {
    try {
      return client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
