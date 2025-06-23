package com.app.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateProxyConfig {

  @Bean
  public RestTemplate socksProxyRestTemplate() {
    String proxyHost = "localhost";
    int proxyPort = 1080;
    return new RestTemplate(new SocksProxySimpleClientHttpRequestFactory(proxyHost, proxyPort));
  }
}
