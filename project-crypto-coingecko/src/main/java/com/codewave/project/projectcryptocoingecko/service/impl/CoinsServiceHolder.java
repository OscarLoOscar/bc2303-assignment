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
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketResp;
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

  private CoinsMarketResp[] getCoinMarket() {
    return restTemplate.getForObject(coinUrl, CoinsMarketResp[].class);
  }

  // @Override
  // public List<CoinsMarketResp> getAllData()  {
  //   try {
  //     // Invoke Coingecko API
  //     CoinsMarketResp[] coinMarkets = getCoinMarket();
  //     log.info("coinUrl" + coinUrl);
  //     // Set the array result to redis
  //     redisService.setCoinMarketResp(coinMarkets); // Async
  //     return Arrays.asList(coinMarkets);
  //   } catch (RestClientException e) {
  //     // Requirement: return last data in redis if the restful call to coingecko fail
  //     return Arrays.asList(redisService.getCoinMarketResp()); // handled JsonProcessingException
  //   }
  // }
  @Override
  public List<CoinsMarketResp> getAllData()   {
    try {
      List<String> params = new ArrayList<>(Arrays.asList(null, "usd"));
      log.info("coinUrl : " + coinUrl);
   CoinsMarketResp[] coins = restTemplate.getForObject(coinUrl, CoinsMarketResp[].class, params.toArray());
      log.info("4 :" + coins);

      redisService.setCoinMarketResp(coins);
      return Arrays.asList(coins);
    } catch (RestClientException e) {
      return Arrays.asList(redisService.getCoinMarketResp());
    }
  }

  // @Override
  // public Map<String, CoinsCurrency> getAllCoinsMarkets(List<String> cryptos, List<String> currencies)
  //     throws BusinessException {
  //   String cryptoStr = cryptos.stream().collect(Collectors.joining(","));
  //   String currencyStr = currencies.stream().collect(Collectors.joining(","));

  //   // Concat Url:
  //   // ?vs_currencies=usd,hkd&ids=bitcoin,ethereum,tether,dogecoin,ripple
  //   HashMap<String, String> hMap = new HashMap<>();
  //   hMap.put("ids", cryptoStr);
  //   hMap.put("vs_currencies", currencyStr);

  //   return CoinsApi.invoke(baseUrl,
  //       serviceVers, simplePriceUrl,
  //       hMap, CoinCurrencyMap.class);

  // }

}
