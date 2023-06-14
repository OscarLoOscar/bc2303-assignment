package com.codewave.project.crypto.coingecko.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.project.crypto.coingecko.infra.util.ApiUtil;
import com.codewave.project.crypto.coingecko.infra.util.UriScheme;

/**
 * @author vincent.lau
 * @apiNote https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false
 */
@Configuration
public class CoinMakertUriBuilderConfig {

  @Value(value = "${api.coingecko.domain}")
  String domain;

  @Value(value = "${api.coingecko.market.endpoint}")
  String endpoint;

  @Value(value = "${api.coingecko.market.pathSegment}")
  String pathSegment;

  @Value(value = "${api.coingecko.market.version}")
  String version;

  @Value(value = "${api.coingecko.market.params.vs-currency}")
  String vsCurrency;

  @Value(value = "${api.coingecko.market.params.order-by}")
  String orderBy;

  @Value(value = "${api.coingecko.market.params.per-page}")
  String perPage;

  @Value(value = "${api.coingecko.market.params.page}")
  String page;

  @Value(value = "${api.coingecko.market.params.sparkline}")
  String sparkline;

  @Bean
  UriComponentsBuilder coinMarketUriBuilder() { // Dangerous, possible to have multiple String Beans
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("vs_currency", vsCurrency);
    queryParams.add("order", orderBy);
    queryParams.add("per_page", perPage);
    queryParams.add("page", page);
    queryParams.add("sparkline", sparkline);
    return ApiUtil.uriBuilder(UriScheme.HTTPS, domain, endpoint, queryParams, pathSegment, version);
  }
}
