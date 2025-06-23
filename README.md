# Spring Boot RestTemplate


## Spring Boot SOCKS Proxy RestTemplate
To configure an application to route HTTP(S) requests through a SOCKS proxy using a custom `RestTemplate`. 

This is useful if:
- You need to access an external service (such as an API) that only allows requests from specific IP addresses.
- Your local development machine is not on the allowed list, but you have SSH access to an authorized server.

---

## How It Works

- You create a SOCKS proxy on your local machine using SSH to the authorized server.
- You configure a Spring `RestTemplate` bean to send all requests via that SOCKS proxy.
- All outgoing HTTP/HTTPS requests made using this bean will be routed through the proxy server and appear as if they originate from the authorized server.

---

## Setting Up the SOCKS Proxy

1. **Open a terminal on your local machine.**

2. **Start a SOCKS proxy with SSH:**

    ```bash
    ssh -D 1080 user@<authorized-server-ip>
    ```

    - Replace `user` with your username on the authorized server.
    - Replace `<authorized-server-ip>` with the server’s public IP or hostname.
    - Leave this terminal window open. The SOCKS proxy will remain active as long as this session runs.

   By default, this creates a SOCKS5 proxy on `localhost:1080`.

---

## RestTemplate Configuration

- Add the following bean configuration in your Spring Boot project:

    ```java
    @Configuration
    public class RestTemplateProxyConfig {
        @Bean
        public RestTemplate socksProxyRestTemplate() {
            String proxyHost = "localhost";
            int proxyPort = 1080;
            return new RestTemplate(new SocksProxySimpleClientHttpRequestFactory(proxyHost, proxyPort));
        }
    }
    ```

---
