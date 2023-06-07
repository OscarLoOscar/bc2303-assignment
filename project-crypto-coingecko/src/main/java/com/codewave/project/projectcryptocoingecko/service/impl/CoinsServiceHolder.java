package com.codewave.project.projectcryptocoingecko.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketResp;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinsCurrency;
import com.codewave.project.projectcryptocoingecko.service.CoinsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CoinsServiceHolder implements CoinsService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  RedisService redisService;

  @Autowired
  @Qualifier(value = "allCoinUrl")
  String coinUrl;

  @Autowired
  @Qualifier(value = "exchangeRateUrl")
  String exchangeRateUrl;

  @Override
  public List<CoinsMarketResp> getAllData() {
    try {
      // Invoke Coingecko API
      CoinsMarketResp[] coins = restTemplate.getForObject(coinUrl, CoinsMarketResp[].class);
      // log.info("step2 : 4 :" + coins);
      // Set the array result to redis
      redisService.setCoinMarketResp(coins); // Async
      return Arrays.asList(coins);
    } catch (RestClientException e) {
      // Requirement: return last data in redis if the restful call to coingecko fail
      return Arrays.asList(redisService.getCoinMarketResp()); // handled JsonProcessingException
    }
  }

  @Override
  public HashMap<String, CoinsCurrency> getExchangeService(List<String> cryptos,
      List<String> currencies)
      throws BusinessException {
    HashMap<String, CoinsCurrency> responseBody = restTemplate.getForObject(exchangeRateUrl, HashMap.class);
    log.info("Map responseBody  " + responseBody);
    log.info("responseBody key " + responseBody.keySet().toString());
    log.info("responseBody value" + responseBody.values());
    return responseBody;
  }
}