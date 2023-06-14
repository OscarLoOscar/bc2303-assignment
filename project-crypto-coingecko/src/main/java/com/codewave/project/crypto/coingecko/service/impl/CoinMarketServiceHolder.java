package com.codewave.project.crypto.coingecko.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.project.crypto.coingecko.model.CoinMarketResp;
import com.codewave.project.crypto.coingecko.service.CoinMarketService;

@Service
public class CoinMarketServiceHolder implements CoinMarketService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  RedisService redisService;

  @Autowired
  @Qualifier("coinMarketUriBuilder")
  UriComponentsBuilder coinMarketUriBuilder;

  private CoinMarketResp[] getCoinMarket() {
    return restTemplate.getForObject(coinMarketUriBuilder.toUriString(), CoinMarketResp[].class);
  }

  @Override
  public List<CoinMarketResp> getCoinMarketWithRedis() {
    try {
      // Invoke Coingecko API
      CoinMarketResp[] coinMarkets = getCoinMarket();
      // Set the array result to redis
      redisService.setCoinMarketResp(coinMarkets); // Async
      return Arrays.asList(coinMarkets);
    } catch (RestClientException e) {
      System.out.println(e.getCause().getMessage());
      // Requirement: return last data in redis if the restful call to coingecko fail
      return Arrays.asList(redisService.getCoinMarketResp()); // handled JsonProcessingException
    }
  }

}
