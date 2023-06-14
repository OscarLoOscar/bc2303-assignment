package com.codewave.project.crypto.coingecko.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.project.crypto.coingecko.infra.util.ApiUtil;
import com.codewave.project.crypto.coingecko.infra.util.UriScheme;

/**
 * @author vincent.lau
 * @apiNote https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false
 */
@Configuration
public class SimplePriceUriBuilderConfig {

  @Value(value = "${api.coingecko.domain}")
  String domain;

  @Value(value = "${api.coingecko.simple-price.endpoint}")
  String endpoint;

  @Value(value = "${api.coingecko.simple-price.pathSegment}")
  String pathSegment;

  @Value(value = "${api.coingecko.simple-price.version}")
  String version;

  @Bean
  UriComponentsBuilder simplePriceUriBuilder() { // Dangerous, possible to have multiple String Beans
    return ApiUtil.uriBuilder(UriScheme.HTTPS, domain, endpoint, pathSegment, version);
  }
}
