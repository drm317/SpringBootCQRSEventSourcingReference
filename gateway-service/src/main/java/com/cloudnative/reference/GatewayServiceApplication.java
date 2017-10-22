package com.cloudnative.reference;

import com.cloudnative.reference.prefilter.ThrottlingPreFilter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@RestController
@EnableCircuitBreaker
public class GatewayServiceApplication {

  @Bean
  public ThrottlingPreFilter throttlingPreFilter() {
    return new ThrottlingPreFilter();
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(GatewayServiceApplication.class).web(true)
        .run(args);
  }

  @RequestMapping("/timeout")
  public String timeout() throws InterruptedException {
    Thread.sleep(80000);
    return "timeout";
  }

  @Bean
  public ZuulFallbackProvider zuulFallbackProvider() {
    return new ZuulFallbackProvider() {
      @Override
      public String getRoute() {
        return "gateway-service";
      }

      @Override
      public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
          @Override
          public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.OK;
          }

          @Override
          public int getRawStatusCode() throws IOException {
            return 200;
          }

          @Override
          public String getStatusText() throws IOException {
            return "OK";
          }

          @Override
          public void close() {

          }

          @Override
          public InputStream getBody() throws IOException {
            return new ByteArrayInputStream("fallback".getBytes());
          }

          @Override
          public HttpHeaders getHeaders() {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return headers;
          }
        };
      }
    };
  }
}
