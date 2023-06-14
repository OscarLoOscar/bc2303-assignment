package com.codewave.project.crypto.polygon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.project.crypto.polygon.infra.enums.UriScheme;
import com.codewave.project.crypto.polygon.infra.util.ApiUtil;

/**
 * @author vincent.lau
 * @apiNote https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false
 */
@Configuration
public class PolygonPriceUrl {

  @Value(value = "${api.polygon.domain}")
  String domain;

  @Value(value = "${api.polygon.prev.endpoint}")
  String endpoint;

  @Value(value = "${api.polygon.prev.version}")
  String version;

  @Value(value = "${api.polygon.prev.params.adjusted}")
  boolean adjusted;

  @Value(value = "${api.polygon.prev.params.api-key}")
  String apiKey;

  @Bean
  public UriComponentsBuilder previousCloseUriBuilder() {
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("adjusted", String.valueOf(adjusted));
    queryParams.add("apiKey", apiKey);
   
    return ApiUtil.uriBuilder(UriScheme.HTTPS, domain, endpoint, queryParams, version);
  }
}
