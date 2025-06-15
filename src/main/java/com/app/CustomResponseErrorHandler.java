package com.app;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

public class CustomResponseErrorHandler implements ResponseErrorHandler {

  private static final Logger log = LoggerFactory.getLogger(CustomResponseErrorHandler.class);

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return response.getStatusCode().isError();
  }

  @Override
  public void handleError(URI url, HttpMethod method, ClientHttpResponse response)
      throws IOException {
    String body = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
    String message =
        String.format(
            "Request %s %s failed with status: %s - Body: %s",
            method.name(), url, response.getStatusCode(), body);

    log.error("HTTP error during request: {}", message);
    throw new RestClientException(message);
  }
}
