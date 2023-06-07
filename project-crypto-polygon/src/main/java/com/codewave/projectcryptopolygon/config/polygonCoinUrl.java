package com.codewave.projectcryptopolygon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.codewave.projectcryptopolygon.infra.util.ApiUtil;
import com.codewave.projectcryptopolygon.infra.util.UriScheme;

@Configuration
public class polygonCoinUrl {
  @Value(value = "${api.polygon.domain}")
  private String domain;
  @Value(value = "${api.polygon.version}")
  private String version;
  @Value(value = "${api.polygon.endpoint}")
  private String endpoint;
  @Value(value = "${api.polygon.path}")
  private String path;
  @Value(value = "${api.polygon.ticker.cryptoTicker}")
  private String cryptoTicker;

  @Value(value = "${api.polygon.params.adjusted}")
  private String adjusted;

  @Value(value = "${api.polygon.params.apiKey}")
  private String apiKey;

  @Bean
  public String polygonUrl() { // very dangerous , possible to have multiple String Beans
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("adjusted", adjusted);
    queryParams.add("apiKey", apiKey);
    return ApiUtil.getUrl(UriScheme.HTTPS, domain, version, endpoint, path, cryptoTicker, "/prev", queryParams);

  }

}
