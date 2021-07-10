package com.noanansi.javahttpclient;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/**
 * <p>Encapsulates all the request response fields for an authorization.</p>
 */
public class AuthCredential {

  public AuthCredential() {
  }

  @SerializedName("access_token")
  private String accessToken;

  @SerializedName("expires_in")
  private int expiresIn;

  @SerializedName("token_type")
  private String tokenType;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final var that = (AuthCredential) o;
    return accessToken.equals(that.accessToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken);
  }

  @Override
  public String toString() {
    return "AuthCredential{"
        + "accessToken='" + accessToken + '\''
        + ", expiresIn=" + expiresIn
        + ", tokenType='" + tokenType + '\''
        + '}';
  }

}
