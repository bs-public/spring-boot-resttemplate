package com.app;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TestService {

  private final RestTemplate restTemplate;
  private final String baseUrl;

  public TestService(RestTemplate restTemplate, HttpBinProperties properties) {
    this.restTemplate = restTemplate;
    this.baseUrl = properties.getBaseUrl();
  }

  public String callGet() {
    return restTemplate.getForObject(baseUrl + "/get", String.class);
  }

  public String callPost() {
    Map<String, String> payload = Map.of("name", "TestName", "city", "TestCity");
    return restTemplate.postForObject(baseUrl + "/post", payload, String.class);
  }

  public String callPut() {
    HttpEntity<Map<String, String>> entity = new HttpEntity<>(Map.of("update", "true"));
    return restTemplate.exchange(baseUrl + "/put", HttpMethod.PUT, entity, String.class).getBody();
  }

  public String callDelete() {
    return restTemplate.exchange(baseUrl + "/delete", HttpMethod.DELETE, null, String.class).getBody();
  }

  public String simulateServerError() {
    return restTemplate.getForObject(baseUrl + "/status/500", String.class);
  }

  public String simulateClientError() {
    return restTemplate.getForObject(baseUrl + "/status/404", String.class);
  }

  public String simulateTimeout() {
    // Simulate delay of 10 seconds — configure RestTemplate timeout to < 10s to trigger timeout
    return restTemplate.getForObject(baseUrl + "/delay/10", String.class);
  }

  public String simulateConnectionRefused() {
    // Try to connect to an unreachable domain to simulate socket exception
    return restTemplate.getForObject("http://localhost:9999/unreachable", String.class);
  }
}
