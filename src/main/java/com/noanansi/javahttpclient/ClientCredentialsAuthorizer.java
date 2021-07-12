package com.noanansi.javahttpclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Optional;

/**
 * Handle Auth0 authorization using client credentials.
 *
 * <p>This object needs the client_id, client_secret, audience and auth_url environment variables
 * initialized. The auth_url refers to the Auth0 domain used by your application
 *
 * @link https://auth0.com/docs/flows/call-your-api-using-the-client-credentials-flow
 */
public class ClientCredentialsAuthorizer {

  private final String clientIdFieldName = "client_id";
  private final String clientSecretFieldName = "client_secret";
  private final String audienceFieldName = "audience";
  private final String audience = System.getenv("audience");
  private final String authUrl = System.getenv("auth_url");
  private final String clientId = System.getenv("client_id");
  private final String clientSecret = System.getenv("client_secret");

  /**
   * Get the access token, its type and expiration time.
   */
  public Optional<AuthCredential> getAccess() {
    final var body = getBody();
    final var tokenUrl = authUrl + "/oauth/token";
    final var headers = Map.of("Content-Type", "application/x-www-form-urlencoded");
    final var result =
        HttpRequestHandler.requestPost(tokenUrl, headers, body, AuthCredential.class);
    return result.getPayload();
  }

  private String getBody() {
    try {
      return String.join("&",
          "grant_type=client_credentials",
          clientIdFieldName + "=" + URLEncoder.encode(clientId, "UTF-8"),
          clientSecretFieldName + "=" + URLEncoder.encode(clientSecret, "UTF-8"),
          audienceFieldName + "=" + URLEncoder.encode(audience, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

}
