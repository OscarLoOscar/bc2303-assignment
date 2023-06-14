package com.codewave.project.crypto.channel.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.codewave.project.crypto.channel.infra.util.MueRestApi;

@Configuration
public class RestTemplateConfig {
  // max no. of connections
  @Value("${rest.conn-mgr.connection.max-total:100}")
  int maxTotal;
  // max no. of connections per route
  @Value("${rest.conn-mgr.connection.max-per-route:200}")
  int maxPerRoute;
  // ms
  @Value("${rest.conn-mgr.check.inactivity:1000}")
  int validateInactivity;

  @Value("${rest.http-client.timeout.evictIdleConnection:5}")
  int evictIdleConnection;

  @Value("${rest.http-request-factory.timeout.pool-connect:5000}")
  int poolConnectTimeout;

  @Value("${rest.http-request-factory.timeout.server-connect:5000}")
  int serverConnectTimeout;

  @Value("${rest.http-request-factory.timeout.server-read:5000}")
  int serverReadTimeout;

  @Bean
  MueRestApi mueRestApi(RestTemplate restTemplate) {
    return new MueRestApi(restTemplate);
  }

  // RestTemplate -> httpRequestFactory -> httpClient -> connectionPoolManager
  @Bean
  RestTemplate restTemplate(RestTemplateBuilder builder) {
    // 設置资源池給 HttpClient
    PoolingHttpClientConnectionManager connectionPoolManager = new PoolingHttpClientConnectionManager();
    // 连接池里面的最大连接数:100
    connectionPoolManager.setMaxTotal(maxTotal);
    // 每个路由默认接收的最大连接数:200
    connectionPoolManager.setDefaultMaxPerRoute(maxPerRoute);

    // Going to Deprecated, to be updated
    // connectionPoolManager.setValidateAfterInactivity(TimeValue.ofMilliseconds(validateInactivity));

    HttpClient httpClient = HttpClientBuilder.create() //
        .setConnectionManager(connectionPoolManager)
        // .setKeepAliveStrategy()
        .evictExpiredConnections()
        .evictIdleConnections(TimeValue.ofSeconds(evictIdleConnection)).build();

    HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    httpRequestFactory.setHttpClient(httpClient);
    // 从连接池获取连接的超时时间，如果连接池里连接都被用了，且超过设定时间,就会报错connectionrequesttimeout
    httpRequestFactory.setConnectionRequestTimeout(poolConnectTimeout);
    // 如果与服务器(这里指数据库)请求建立连接的时间超过ConnectionTimeOut，就会抛ConnectionTimeOutException，即服务器连接超时，没有在规定的时间内建立连接
    httpRequestFactory.setConnectTimeout(serverConnectTimeout);
    // 客户端和服务器建立连接后,客户端从服务器读取数据的超时时间,超出后会抛出SocketTimeoutException:20000
    // @Deprecated, check SocketConfig
    // httpRequestFactory.setReadTimeout(serverReadTimeout);

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(httpRequestFactory);
    // MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new
    // MappingJackson2HttpMessageConverter();
    // mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
    // MediaType.TEXT_HTML,
    // MediaType.TEXT_PLAIN));
    // restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
    return restTemplate;
  }

}
