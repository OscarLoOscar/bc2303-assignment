package com.codewave.projectcryptopolygon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.codewave.projectcryptopolygon.infra.util.ApiUtil;
import com.codewave.projectcryptopolygon.infra.util.UriScheme;

@Configuration
public class CoinMarketUrlConfig {
  @Value(value = "${api.polygon.domain}")
  private String domain;

  @Value(value = "${api.polygon.version}")
  private String version;

  @Value(value = "${api.polygon.endpoint}")
  private String endpoint;

  @Value(value = "${api.polygon.ticker.cryptoTicker}")
  private String cryptoTicker;

  @Value(value = "${api.polygon.range.multiplier}")
  private String multiplier;

  @Value(value = "${api.polygon.range.timespan}")
  private String timespan;

  @Value(value = "${api.polygon.range.from}")
  private String from;

  @Value(value = "${api.polygon.range.to}")
  private String to;

  @Value(value = "${api.polygon.params.adjusted}")
  private String locale;

  @Value(value = "${api.polygon.params.sort}")
  private String sort;

  @Value(value = "${api.polygon.params.limit}")
  private String limit;

  @Value(value = "${api.polygon.params.apiKey}")
  private String apiKey;

  @Bean
  public String polygonCoinUrl() { // very dangerous , possible to have multiple String Beans
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("cryptoTicker", cryptoTicker);
    queryParams.add("multiplier", multiplier);
    queryParams.add("timespan", timespan);
    queryParams.add("from", from);
    queryParams.add("to", to);
    queryParams.add("locale", locale);
    queryParams.add("sort", sort);
    queryParams.add("limit", limit);
    return ApiUtil.getUrl(UriScheme.HTTPS, domain, endpoint, queryParams, version, apiKey);
  }

}
