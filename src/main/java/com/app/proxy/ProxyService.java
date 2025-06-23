package com.app.proxy;

import com.app.HttpBinProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProxyService {
  private final RestTemplate restTemplate;
  private final String baseUrl;

  public ProxyService(RestTemplate socksProxyRestTemplate, HttpBinProperties properties) {
    this.restTemplate = socksProxyRestTemplate;
    this.baseUrl = properties.getBaseUrl();
  }

  public String callGet() {
    return restTemplate.getForObject(baseUrl + "/get", String.class);
  }
}
