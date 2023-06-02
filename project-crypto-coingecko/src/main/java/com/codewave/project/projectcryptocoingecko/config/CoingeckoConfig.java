package com.codewave.project.projectcryptocoingecko.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.codewave.project.projectcryptocoingecko.infra.util.ApiUtil;
import com.codewave.project.projectcryptocoingecko.infra.util.UriScheme;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CoingeckoConfig {
  // https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en
  // https://api.coingecko.com/api/v3/coins/markets?ids=bitcoin,ethereum,tether,dogecoin,ripple&vs_currency=usd

  @Value("${coingecko.baseUrl:api.coingecko.com}")
  private String coingeckoDomain;
  // add
  @Value("${coingecko.serviceVers:/api/v3}")
  private String coinsServerPort;
  //
  @Value("${coingecko.service.coins-markets.serviceUrl:/coins/markets}")
  private String coinsEndpoint;

  @Value("${coingecko.service.coins-markets.currency}")
  private String currency;

  @Value("${coingecko.service.coins-markets.order}")
  private String order;

  @Value("${coingecko.service.coins-markets.perPage}")
  private String perPage;

  @Value("${coingecko.service.coins-markets.page}")
  private String page;

  @Value("${coingecko.service.coins-markets.sparkline}")
  private String sparkline;

  @Value("${coingecko.service.simple-price.serviceUrl}")
  private String simplePriceEndpoint;

  @Bean
  public CorsFilter corsFilter() {
    // SpringMvc 提供了 CorsFilter 过滤器

    // 初始化cors配置对象
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    // 允许跨域的域名，如果要携带cookie,不要写*，*：代表所有域名都可以跨域访问
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.setAllowCredentials(true); // 设置允许携带cookie
    corsConfiguration.addAllowedMethod("*"); // 代表所有的请求方法：GET POST PUT DELETE...
    corsConfiguration.addAllowedHeader("*"); // 允许携带任何头信息

    // 初始化cors配置源对象
    UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
    corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

    // 返回corsFilter实例，参数：cors配置源对象
    return new CorsFilter(corsConfigurationSource);
  }

  @Bean // The returned Object will be placed into Spring Context
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean // The returned Object will be placed into Spring Context
  ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    // objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    return objectMapper;
  }

  @Bean
  @Qualifier("allCoinUrl")
  public String allCoinUrl() { // very dangerous
    String baseUrl = ApiUtil.getUrl(UriScheme.HTTPS, coingeckoDomain, coinsServerPort, coinsEndpoint);
    String params = "?vs_currency=" + currency + "&order=" + order + "&per_page=" + perPage + "&page=" + page
        + "&sparkline=" + sparkline + "&locale=en";
    return baseUrl + params;
  }
}