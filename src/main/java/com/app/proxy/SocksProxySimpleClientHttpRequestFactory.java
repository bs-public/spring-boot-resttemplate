package com.app.proxy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class SocksProxySimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory {
  private final String proxyHost;
  private final int proxyPort;

  public SocksProxySimpleClientHttpRequestFactory(String proxyHost, int proxyPort) {
    this.proxyHost = proxyHost;
    this.proxyPort = proxyPort;
  }

  @Override
  protected HttpURLConnection openConnection(URL url, Proxy proxy) throws IOException {
    // Ignore the passed-in proxy, use our own SOCKS proxy instead
    Proxy socksProxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyHost, proxyPort));
    return (HttpURLConnection) url.openConnection(socksProxy);
  }
}
