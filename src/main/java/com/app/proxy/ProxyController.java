package com.app.proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

  private final ProxyService proxyService;

  public ProxyController(ProxyService proxyService) {
    this.proxyService = proxyService;
  }

  @GetMapping("/proxy/get")
  public ResponseEntity<String> testGet() {
    return ResponseEntity.ok(proxyService.callGet());
  }
}
