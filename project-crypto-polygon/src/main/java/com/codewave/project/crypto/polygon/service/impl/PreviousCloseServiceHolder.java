package com.codewave.project.crypto.polygon.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.project.crypto.polygon.infra.enums.Currency;
import com.codewave.project.crypto.polygon.model.PreviousClose;
import com.codewave.project.crypto.polygon.service.PreviousCloseService;

@Service
public class PreviousCloseServiceHolder implements PreviousCloseService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisService redisService;

  @Autowired
  @Qualifier("previousCloseUriBuilder")
  private UriComponentsBuilder previousCloseUriBuilder;

  private PreviousClose getPreviousClose(Currency coin, Currency tradCurr) {
    // Prepare the PathVariable by Map
    Map<String, String> pathVariables = new HashMap<>();
    pathVariables.put("COINS", coin.name() + tradCurr.name());
    // Handle PathVariable by UriComponentsBuilder.build()
    // X:{COINS} -> X:BTCUSD
    String previousCloseUrl = previousCloseUriBuilder.build(pathVariables).toString();
    // System.out.println("previousCloseUrl=" + previousCloseUrl);
    return restTemplate.getForObject(previousCloseUrl, PreviousClose.class);
  }

  @Override
  public PreviousClose getPreviousCloseWithRedis(Currency fromCurr, Currency toCurr) {
    try {
      Currency coin = null;
      // Currency tradCurr = null;
      if (fromCurr.isCrypto()) {
        coin = fromCurr;
        // tradCurr = toCurr;
      } else {
        coin = toCurr;
        // tradCurr = fromCurr;
      }
      // Invoke Coingecko API
      PreviousClose previousClose = getPreviousClose(coin, Currency.USD);
      // if (tradCurr == Currency.HKD) {
      //   BigDecimal hkd = new BigDecimal(7.8).multiply(previousClose.getResults().get(0).getClosePrice());
      //   previousClose.getResults().get(0).setClosePrice(hkd);
      // }
      // Set the array result to redis
      redisService.setSimplePriceResp(previousClose); // Async
      return previousClose;
    } catch (RestClientException e) {
      // Requirement: return last data in redis if the restful call to coingecko fail
      System.out.println("error=" + e.getMessage());
      return redisService.getSimplePriceResp(); // handled JsonProcessingException
    }
  }

}
