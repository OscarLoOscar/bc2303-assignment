package com.codewave.project.projectcryptocoingecko.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.infra.response.CoinsApi;
import com.codewave.project.projectcryptocoingecko.model.CoinCurrencyMap;
import com.codewave.project.projectcryptocoingecko.model.CoinsCurrency;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;
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
  CoinsApi coinsApi;

  @Value("${coingecko.baseUrl}")
  String baseUrl;
  @Value("${coingecko.serviceVers}")
  String serviceVers;
  @Value("${coingecko.service.coins-markets.serviceUrl}")
  String serviceUrl;
  @Value("${coingecko.service.coins-markets.currency}")
  String vsCurrency;
  @Value("${coingecko.service.coins-markets.order}")
  String order;
  @Value("${coingecko.service.coins-markets.perPage}")
  String perPage;
  @Value("${coingecko.service.coins-markets.page}")
  String page;
  @Value("${coingecko.service.coins-markets.sparkline}")
  String sparkline; // boolean
  @Value("${coingecko.service.simple-price.serviceUrl}")
  String simplePriceUrl;

  @Override
  public List<CoinMarketRespDto> getAllData() throws BusinessException {
    try {
      List<String> params = new ArrayList<>(Arrays.asList(null, "usd"));
      log.info("coinUrl : " + coinUrl);
      log.info("3 :" + baseUrl);
      CoinMarketRespDto[] coins = restTemplate.getForObject(coinUrl, CoinMarketRespDto[].class, params.toArray());
      log.info("4 :" + coins);

      redisService.setCoins(coins);
      return Arrays.asList(coins);
    } catch (RestClientException e) {
      return Arrays.asList(redisService.getCoins());
    }
  }

  @Override
  public Map<String, CoinsCurrency> getAllCoinsMarkets(List<String> cryptos, List<String> currencies)
      throws BusinessException {
    String cryptoStr = cryptos.stream().collect(Collectors.joining(","));
    String currencyStr = currencies.stream().collect(Collectors.joining(","));

    // Concat Url:
    // ?vs_currencies=usd,hkd&ids=bitcoin,ethereum,tether,dogecoin,ripple
    HashMap<String, String> hMap = new HashMap<>();
    hMap.put("ids", cryptoStr);
    hMap.put("vs_currencies", currencyStr);

    return coinsApi.invoke(baseUrl,
        serviceVers, simplePriceUrl,
        hMap, CoinCurrencyMap.class);

  }

}
