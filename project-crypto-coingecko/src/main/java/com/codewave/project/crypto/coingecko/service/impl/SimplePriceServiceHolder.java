package com.codewave.project.crypto.coingecko.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.project.crypto.coingecko.config.CoinsMappingConfig;
import com.codewave.project.crypto.coingecko.infra.enums.Currency;
import com.codewave.project.crypto.coingecko.model.SimplePriceResp;
import com.codewave.project.crypto.coingecko.service.SimplePriceService;

@Service
public class SimplePriceServiceHolder implements SimplePriceService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  RedisService redisService;

  @Autowired
  @Qualifier("simplePriceUriBuilder")
  UriComponentsBuilder simplePriceUriBuilder;

  @Override
  public SimplePriceResp getSimplePriceWithRedis(List<Currency> fromCurrs, List<Currency> toCurrs) {
    try {
      // Invoke Coingecko API
      List<Currency> coins = null;
      List<Currency> tradCurrs = null;
      if (fromCurrs.get(0).isCrypto()) {
        coins = fromCurrs;
        tradCurrs = toCurrs;
      } else {
        coins = toCurrs;
        tradCurrs = fromCurrs;
      }
      SimplePriceResp simplePriceResp = getSimplePrice(coins, tradCurrs);
      // Set the array result to redis
      redisService.setSimplePriceResp(simplePriceResp); // Async
      return simplePriceResp;
    } catch (RestClientException e) {
      System.out.println(e.getCause().getMessage());
      // Requirement: return last data in redis if the restful call to coingecko fail
      return redisService.getSimplePriceResp(); // handled JsonProcessingException
    }
  }

  private SimplePriceResp getSimplePrice(List<Currency> coins, List<Currency> tradCurrs) {
    // Convert List of enums to a string separated by comma
    String coinsStr = coins.stream()
        .map(coin -> coinMap(coin))
        .collect(Collectors.joining(","));
    String tradCurrStr = tradCurrs.stream()
        .map(curr -> coinMap(curr))
        .collect(Collectors.joining(","));
    // Prepare the query parameters
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("ids", coinsStr);
    queryParams.add("vs_currencies", tradCurrStr);
    // Add query parameters into the URI builder, and return url string
    String url = simplePriceUriBuilder.queryParams(queryParams).toUriString();
    System.out.println("url=" + url);
    // Rest Call
    return restTemplate.getForObject(url, SimplePriceResp.class);
  }

  private static String coinMap(Currency coin) {
    return CoinsMappingConfig.get(coin); // bitcoin
  }

}
