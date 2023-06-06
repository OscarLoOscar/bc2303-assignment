package com.codewave.project.projectcryptocoingecko.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.codewave.project.projectcryptocoingecko.infra.enums.Currency;
import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketResp;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.ChannelDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinsCurrency;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.ChannelDto.ExchangeRate;
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
  private static String exchangeRateUrl;

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
  public HashMap<String, CoinsCurrency> getSimplePricesByMap(List<String> cryptos, List<String> currencies)
      throws BusinessException {
    log.info("before :" + cryptos.toString());// [Bitcoin, dogecoin, ETH, tether]
    log.info("before :" + currencies.toString());// [usd, hkd]
    // Alternative: String.join(",", cryptos);
    // Change the List<String> to String (bitcoin,tether)
    // incorrect path
    HashMap<String, CoinsCurrency> hMap = new LinkedHashMap<>();
    for (String crypto : cryptos) {
      CoinsCurrency coinsCurrency = new CoinsCurrency();
      try {
        coinsCurrency.setUsd(new BigDecimal(currencies.get(0)));
        coinsCurrency.setHkd(new BigDecimal(currencies.get(1)));
      } catch (NumberFormatException e) {
        log.error("Error converting currency value to BigDecimal: " + e.getMessage());
      }
      log.info("after : " + coinsCurrency.toString());
      hMap.put(crypto, coinsCurrency);
      log.info("after map : " + hMap.toString());
    }
    return hMap;
  }

  @Override
  public HashMap<String, List<String>> getExchangeService(List<String> cryptos, List<String> currencies)
      throws BusinessException {
    HashMap<String, List<String>> hMap = new HashMap<>();
    for (String crypto : cryptos) {
      hMap.put(crypto, new ArrayList<>());
      for (String currency : currencies) {
        hMap.get(crypto).add(currency);
      }
    }
    log.info("After map " + hMap.toString());
    return hMap;
  }
}
