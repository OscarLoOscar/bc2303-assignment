package com.codewave.project.projectcryptocoingecko.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.codewave.project.projectcryptocoingecko.infra.util.ApiUtil;
import com.codewave.project.projectcryptocoingecko.infra.util.UriScheme;

@Configuration
public class ExchangeRateUrlConfig {
  @Value(value = "${api.coingecko.domain}")
  private String domain;

  @Value(value = "${api.coingecko.simple.endpoint2}")
  private String endpoint2;

  @Value(value = "${api.coingecko.simple.pathSegment}")
  private String pathSegment;

  @Value(value = "${api.coingecko.simple.version}")
  private String version;

  @Value(value = "${api.coingecko.simple.params2.ids}")
  private String ids;

  @Value(value = "${api.coingecko.simple.params2.vs_currencies}")
  private String vsCurrencies;

  @Bean
  public String exchangeRateUrl() { // very dangerous , possible to have multiple String Beans
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>(); //correct
     queryParams.add("ids", ids);
     queryParams.add("vs_currencies", vsCurrencies);
   
    return ApiUtil.getUrl(UriScheme.HTTPS, domain, endpoint2, "/price",queryParams, pathSegment, version);
  }

}
