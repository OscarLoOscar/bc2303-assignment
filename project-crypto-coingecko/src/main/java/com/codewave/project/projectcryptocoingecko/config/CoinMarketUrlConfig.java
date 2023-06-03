package com.codewave.project.projectcryptocoingecko.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.codewave.project.projectcryptocoingecko.infra.util.ApiUtil;
import com.codewave.project.projectcryptocoingecko.infra.util.UriScheme;

@Configuration
public class CoinMarketUrlConfig {
  @Value(value = "${api.coingecko.domain}")
  private String domain;

  @Value(value = "${api.coingecko.market.endpoint}")
  private String endpoint;

  @Value(value = "${api.coingecko.market.pathSegment}")
  private String pathSegment;

  @Value(value = "${api.coingecko.market.version}")
  private String version;

  @Value(value = "${api.coingecko.market.params.vs_currency}")
  private String vsCurrency;

  @Value(value = "${api.coingecko.market.params.order}")
  private String order;

  @Value(value = "${api.coingecko.market.params.per_page}")
  private String perPage;

  @Value(value = "${api.coingecko.market.params.page}")
  private String page;

  @Value(value = "${api.coingecko.market.params.sparkline}")
  private String sparkline;

  @Value(value = "${api.coingecko.market.params.locale}")
  private String locale;


  @Bean
  public String allCoinUrl() { // very dangerous , possible to have multiple String Beans
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("vs_currency", vsCurrency);
    queryParams.add("order", order);
    queryParams.add("per_page", perPage);
    queryParams.add("page", page);
    queryParams.add("sparkline", sparkline);
    queryParams.add("locale", locale);
    return ApiUtil.getUrl(UriScheme.HTTPS, domain, endpoint, queryParams, pathSegment, version);
  }

}
