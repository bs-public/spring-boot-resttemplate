package com.app;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TestService {

  private final RestTemplate restTemplate;

  public TestService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String callGet() {
    return restTemplate.getForObject("https://httpbin.org/get", String.class);
  }

  public String callPost() {
    Map<String, String> payload = Map.of("name", "TestName", "city", "TestCity");
    return restTemplate.postForObject("https://httpbin.org/post", payload, String.class);
  }

  public String callPut() {
    HttpEntity<Map<String, String>> entity = new HttpEntity<>(Map.of("update", "true"));
    return restTemplate
        .exchange("https://httpbin.org/put", HttpMethod.PUT, entity, String.class)
        .getBody();
  }

  public String callDelete() {
    return restTemplate
        .exchange("https://httpbin.org/delete", HttpMethod.DELETE, null, String.class)
        .getBody();
  }
}
