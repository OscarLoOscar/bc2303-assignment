package com.codewave.projectcryptopolygon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.projectcryptopolygon.infra.util.ApiUtil;
import com.codewave.projectcryptopolygon.infra.util.UriScheme;

@Configuration
public class CoinMarketUrlConfig {
  @Value(value = "${api.polygon.domain}")
  private String domain;// api.polygon.io

  @Value(value = "${api.polygon.simple.version}")
  private String version;// v2

  @Value(value = "${api.polygon.simple.endpoint}")
  private String endpoint;// aggs/ticker

  @Value(value = "${api.polygon.simple.ticker.cryptoTicker}")
  private String cryptoTicker;

  @Value(value = "${api.polygon.simple.range.multiplier}")
  private String multiplier;

  @Value(value = "${api.polygon.simple.range.timespan}")
  private String timespan;

  @Value(value = "${api.polygon.simple.range.from}")
  private String from;

  @Value(value = "${api.polygon.simple.range.to}")
  private String to;

  @Value(value = "${api.polygon.simple.params.adjusted}")
  private String adjusted;

  @Value(value = "${api.polygon.simple.params.sort}")
  private String sort;

  @Value(value = "${api.polygon.simple.params.limit}")
  private String limit;

  @Value(value = "${api.polygon.simple.params.apiKey}")
  private String apiKey;// 0pBc6wIRoFmb0jiNfpQkQtphDb9u_e7K

  @Bean
  public String polygonCoinUrl() {
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("adjusted", adjusted);
    queryParams.add("sort", sort);
    queryParams.add("limit", limit);
    queryParams.add("apiKey", apiKey);

    return ApiUtil.getUrl(UriScheme.HTTPS,
        domain, version, endpoint, cryptoTicker, "/range", multiplier, timespan, from, to, queryParams);

  }
}
